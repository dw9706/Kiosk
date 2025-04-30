package com.dongwon.kiosk.challenge.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Kiosk {
    private final List<Menu> menus = new ArrayList<>();
    private final Cart cart = new Cart();
    private final Scanner sc = new Scanner(System.in);

    public Kiosk(Menu... menus) {
        this.menus.addAll(Arrays.asList(menus));
    }

    public void start() {
        while (true) {
            try {
                //주문 할 때 주문 번호와 취소 번호 미리 초기화
                int orderIndex = menus.size();
                int orderCancelIndex = menus.size() + 1;


                // 메뉴 목록 출력
                printMenus();
                // 장바구니가 비어져 있지 않다면 주문 메뉴 출력
                if (!cart.isEmpty()) printOrderMenu();

                System.out.print("숫자를 입력해주세요: ");
                int displayNum = Integer.parseInt(sc.nextLine());
                int menuIndex = toIndex(displayNum);
                if (menuIndex == -1) break;

                // 장바구니가 비어져있는지 확인 -> 입력값이 주문 메뉴 번호값인지 확인
                if (!cart.isEmpty() && isOrderMenu(menuIndex)) {
                    // 주문
                    if (menuIndex == orderIndex && askOrder()) {
                        // 할인 정보를 출력 후 사용자가 선택한 할인율을 반환 받음.
                        double discountRate = selectDiscountType();
                        order(discountRate);
                    // 취소
                    } else if (menuIndex == orderCancelIndex) {
                        cancelOrder();
                    }
                    continue;
                }

                // 메뉴 아이템 출력
                printMenuItems(menuIndex);
                System.out.print("숫자를 입력해주세요: ");
                int menuItemNo = Integer.parseInt(sc.nextLine());
                int menuItemIndex = toIndex(menuItemNo);
                if (menuItemIndex == -1) continue;

                // 선택된 메뉴 아이템 출력
                printSelectMenuItem(menuIndex, menuItemIndex);
                // 선택한 아이템을 장바구니에 추가할지 확인하고, 입력값에 따라 아이템을 장바구니에 추가.
                askAddMenuItemToCart(menuIndex, menuItemIndex);

                // 모든 예외는 언체크로 던져져 이곳에서 처리.
            } catch (IllegalArgumentException e) {
                System.out.println("올바르지 않은 입력값입니다: " + e.getMessage() + "\n");
            }
        }

        System.out.println("프로그램을 종료합니다.");
    }


    private void printMenus() {
        System.out.println("\n[ MAIN MENU ]");
        IntStream.range(0, menus.size()).mapToObj(i -> toDisplayNum(i) + ". " + menus.get(i).getCategory()).forEach(System.out::println);
        System.out.println("0. 종료      | 종료");
    }

    private void printOrderMenu() {
        int ordersIndex = menus.size();
        int cancelIndex = menus.size() + 1;
        System.out.println("\n[ ORDER MENU ]");
        System.out.printf("%d. Orders       | 장바구니를 확인 후 주문합니다.\n", toDisplayNum(ordersIndex));
        System.out.printf("%d. Cancel       | 진행중인 주문을 취소합니다.\n", toDisplayNum(cancelIndex));
    }

    private boolean isOrderMenu(int menuIndex) {
        int ordersIndex = menus.size();
        int cancelIndex = menus.size() + 1;
        return (menuIndex == ordersIndex || menuIndex == cancelIndex);
    }

    private boolean askOrder() {
        while (!cart.isEmpty()) {
            System.out.println("아래와 같이 주문 하시겠습니까?\n");
            System.out.println("[ Orders ]");

            cart.getCartItems().forEach(System.out::println);

            System.out.println("\n[ Total ]");
            System.out.printf("W %.1f\n\n", cart.getTotalPrice());
            System.out.println("1. 주문      2. 메뉴 뺴기     3. 메뉴판");

            int input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    return true;
                case 2: {
                    System.out.print("빼실 메뉴 이름을 입력해주세요: ");
                    String excludeMenu = sc.nextLine().trim();
                    excludeMenuItem(excludeMenu);
                    break;
                }
                default:
                    throw new IllegalArgumentException(String.valueOf(input));
            }

        }
        return false;
    }

    private double selectDiscountType() {
        System.out.println("할인 정보를 입력해주세요.");
        for (DiscountType value : DiscountType.values()) {
            System.out.println(value.getDisplayNum() + ". " + value);
        }

        int displayNum = Integer.parseInt(sc.nextLine());
        // 선택한 할인 종류의 번호를 통해 Enum 타입을 반환받고 그 타입의 할인율을 반환
        return DiscountType.get(displayNum).getDiscountRate();
    }

    private void excludeMenuItem(String excludeMenu) {
        // 제외 메뉴를 제외하고 MenuItem 리스트를 새로 만든다.
        List<MenuItem> items = cart.getCartItems().stream().filter(item -> !item.getName().equalsIgnoreCase(excludeMenu)).toList();
        // 기존 MenuItem리스트를 제외 메뉴를 제외하고 새로 만든 MenuItem리스트로 변경한다.
        cart.changeCartItems(items);
    }

    private void cancelOrder() {
        System.out.println("주문을 취소합니다.");
        cart.clear();
    }

    private void order(double discountRate) {
        double totalPrice = cart.getTotalPrice();
        double discountPrice = totalPrice * discountRate;
        double finalPrice = totalPrice - discountPrice;
        System.out.println("주문이 완료되었습니다.");
        System.out.printf("주문 금액 : %.1f, 할인 금액 : %.2f\n", totalPrice, discountPrice);
        System.out.printf("금액은 W %.2f 입니다.\n", finalPrice);
        cart.clear();
    }

    private void printMenuItems(int menuIndex) {
        // 메뉴 인덱스 유효성 검증
        if (0 > menuIndex || menuIndex >= menus.size())
            throw new IllegalArgumentException(String.valueOf(toDisplayNum(menuIndex)));

        Menu menu = menus.get(menuIndex);
        String categoryName = menu.getCategory().toUpperCase();
        List<MenuItem> menuItems = menus.get(menuIndex).getMenuItems();

        System.out.println("\n[ " + categoryName + " MENU ]");

        IntStream.range(0, menuItems.size()).mapToObj(i -> toDisplayNum(i) + ". " + menuItems.get(i)).forEach(System.out::println);
        System.out.println("0. 뒤로가기");
    }

    private void printSelectMenuItem(int menuIndex, int menuItemIndex) {
        List<MenuItem> menuItems = menus.get(menuIndex).getMenuItems();
        // 메뉴 아이템 인덱스 유효성 검증
        if (0 > menuItemIndex || menuItemIndex >= menuItems.size())
            throw new IllegalArgumentException(String.valueOf(toDisplayNum(menuItemIndex)));

        System.out.print("선택한 메뉴: ");
        System.out.println(menuItems.get(menuItemIndex) + "\n");
    }

    private void askAddMenuItemToCart(int menuIndex, int menuItemIndex) {
        MenuItem menuItem = menus.get(menuIndex).getMenuItems().get(menuItemIndex);
        System.out.printf("\"%s\"\n", menuItem);
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
        int input = Integer.parseInt(sc.nextLine());
        switch (input) {
            case 1:
                cart.addItem(menuItem);
                System.out.println(menuItem.getName() + " 이 장바구니에 추가되었습니다.");
                break;
            case 2: {
                break;
            }
            default:
                throw new IllegalArgumentException(String.valueOf(input));
        }
    }

    /*
     * 입력값을 프로그램 내부에서 사용하는 메뉴 인덱스 값으로 변환
     * */
    private int toIndex(int no) {
        return no - 1;
    }

    /*
     * 프로그램 내부의 메뉴 인덱스 값을 고객에게 보여지는 메뉴 넘버로 변환
     * */
    private int toDisplayNum(int i) {
        return i + 1;
    }
}


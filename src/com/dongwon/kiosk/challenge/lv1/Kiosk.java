package com.dongwon.kiosk.challenge.lv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
                int orderCancelIndex = menus.size()+1;


                // 메뉴 목록 출력
                printMenus();
                if (!cart.isEmpty()) printOrderMenu();

                System.out.print("숫자를 입력해주세요: ");
                int menuNo = Integer.parseInt(sc.nextLine());
                int menuIndex = toMenuIndex(menuNo);
                if (menuIndex == -1) break;

                if (!cart.isEmpty() && isOrderMenu(menuIndex)) {
                    if (menuIndex == orderIndex && askOrder()) {
                        order();
                    } else if (menuIndex == orderCancelIndex) {
                        cancelOrder();
                    }
                    continue;
                }

                // 메뉴 아이템 출력
                printMenuItems(menuIndex);
                System.out.print("숫자를 입력해주세요: ");
                int menuItemNo = Integer.parseInt(sc.nextLine());
                int menuItemIndex = toMenuIndex(menuItemNo);
                if (menuItemIndex == -1) continue;

                printSelectMenuItem(menuIndex, menuItemIndex);
                askAddMenuItemToCart(menuIndex, menuItemIndex);

            } catch (IllegalArgumentException e) {
                System.out.println("올바르지 않은 입력값입니다: " + e.getMessage() + "\n");
            }
        }

        System.out.println("프로그램을 종료합니다.");
    }


    private void printMenus() {
        System.out.println("\n[ MAIN MENU ]");
        for (int i = 0; i < menus.size(); i++) {
            int menuNo = toMenuNo(i);
            System.out.println(menuNo + ". " + menus.get(i).getCategory());
        }
        System.out.println("0. 종료      | 종료");
    }

    private void printOrderMenu() {
        int ordersIndex = menus.size();
        int cancelIndex = menus.size() +  1;
        System.out.println("\n[ ORDER MENU ]");
        System.out.printf("%d. Orders       | 장바구니를 확인 후 주문합니다.\n", toMenuNo(ordersIndex));
        System.out.printf("%d. Cancel       | 진행중인 주문을 취소합니다.\n", toMenuNo(cancelIndex));
    }

    private boolean isOrderMenu(int menuIndex) {
        int ordersIndex = menus.size();
        int cancelIndex = menus.size() +  1;
        return (menuIndex == ordersIndex || menuIndex == cancelIndex);
    }

    private boolean askOrder() {
        System.out.println("아래와 같이 주문 하시겠습니까?\n");
        System.out.println("[ Orders ]");
        for (MenuItem cartItem : cart.getCartItems()) {
            System.out.println(cartItem);
        }
        System.out.println("\n[ Total ]");
        System.out.println("W " + cart.getTotalPrice() + "\n");
        System.out.println("1. 주문      2. 메뉴판");

        int input = Integer.parseInt(sc.nextLine());

        return switch (input) {
            case 1 -> true;
            case 2 -> false;
            default -> throw new IllegalArgumentException(String.valueOf(input));
        };
    }

    private void cancelOrder() {
        System.out.println("주문을 취소합니다.");
        cart.clear();
    }

    private void order() {
        System.out.printf("주문이 완료되었습니다. 금액은 W %.1f 입니다.\n", cart.getTotalPrice());
        cart.clear();
    }

    private void printMenuItems(int menuIndex) {
        // 메뉴 인덱스 유효성 검증
        if (0 > menuIndex || menuIndex >= menus.size())
            throw new IllegalArgumentException(String.valueOf(toMenuNo(menuIndex)));

        Menu menu = menus.get(menuIndex);
        String categoryName = menu.getCategory().toUpperCase();
        List<MenuItem> menuItems = menus.get(menuIndex).getMenuItems();

        System.out.println("\n[ " + categoryName + " MENU ]");
        for (int i = 0; i < menuItems.size(); i++) {
            int menuNo = toMenuNo(i);
            System.out.println(menuNo + ". " + menuItems.get(i));
        }
        System.out.println("0. 뒤로가기");
    }

    private void printSelectMenuItem(int menuIndex, int menuItemIndex) {
        List<MenuItem> menuItems = menus.get(menuIndex).getMenuItems();
        // 메뉴 아이템 인덱스 유효성 검증
        if (0 > menuItemIndex || menuItemIndex >= menuItems.size())
            throw new IllegalArgumentException(String.valueOf(toMenuNo(menuItemIndex)));

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
    private int toMenuIndex(int no) {
        return no - 1;
    }

    /*
     * 프로그램 내부의 메뉴 인덱스 값을 고객에게 보여지는 메뉴 넘버로 변환
     * */
    private int toMenuNo(int i) {
        return i + 1;
    }
}


package com.dongwon.kiosk.required.lv4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<Menu> menus = new ArrayList<>();
    //  Scanner를 인스턴스 변수로 선언하여 여러 메서드 영역에서 자유롭게 접근.
    private final Scanner sc = new Scanner(System.in);

    public Kiosk(Menu... menus) {
        this.menus.addAll(Arrays.asList(menus));
    }

    public void start() {
        while (true) {
            try {
                // 메뉴 목록 출력
                printMenus();
                System.out.print("숫자를 입력해주세요: ");
                int menuNo = Integer.parseInt(sc.nextLine());
                int menuIndex = toMenuIndex(menuNo);
                if(menuIndex == -1) break;

                // 메뉴 아이템 목록 출력
                printMenuItems(menuIndex);
                System.out.print("숫자를 입력해주세요: ");
                int menuItemNo = Integer.parseInt(sc.nextLine());
                int menuItemIndex = toMenuIndex(menuItemNo);
                if(menuItemIndex == -1) continue;

                // 선택된 메뉴 아이템 출력
                printSelectMenuItem(menuIndex, menuItemIndex);

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

    private void printMenuItems(int menuIndex) {
        // 메뉴 인덱스 유효성 검증
        if (0 > menuIndex || menuIndex >= menus.size()) throw new IllegalArgumentException(String.valueOf(toMenuNo(menuIndex)));

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
        // menuIndex는 printMenuItems 에서 검증했기 때문에 하지 않음.
        if (0 > menuItemIndex || menuItemIndex >= menuItems.size()) throw new IllegalArgumentException(String.valueOf(toMenuNo(menuItemIndex)));

        System.out.print("선택한 메뉴: ");
        // menuItem에 toString을 재정의하여 객체를 바로 출력
        System.out.println(menuItems.get(menuItemIndex) + "\n");
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


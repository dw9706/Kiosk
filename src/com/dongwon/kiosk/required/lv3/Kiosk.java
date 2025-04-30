package com.dongwon.kiosk.required.lv3;

import java.util.*;
public class Kiosk {
    private List<MenuItem> menus = new ArrayList<>();

    public Kiosk(MenuItem... menuItems) {
        menus.addAll(Arrays.asList(menuItems));
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            //메뉴 출력
            System.out.println("[ SHAKESHACK MENU ]");
            for (int i = 0; i < menus.size(); i++) {
                int menuNo = toMenuNo(i);
                System.out.print(menuNo + ". ");
                System.out.println(menus.get(i));
            }
            System.out.println("0. 종료      | 종료");

            System.out.print("숫자를 입력해주세요.(0~4사이 숫자): ");
            try {
                int input = Integer.parseInt(sc.nextLine());


                // 입력값을 프로그램 내부에서 사용하는 인덱스 값으로 변환
                int menuIndex = toMenuIndex(input);
                int menuNo = toMenuNo(menuIndex);

                // 입력값 유효성 검증
                if (0 <= menuIndex && menuIndex <= 3) System.out.println(menuNo + ". " + menus.get(menuIndex) + "\n");
                else if (menuIndex == -1) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                } else System.out.println("올바르지 않은 입력값입니다 : " + input + "\n");
            } catch (IllegalArgumentException e) {
                System.out.println("올바르지 않은 입력값입니다 - " + e.getMessage() + "\n");
            }
        }
    }

    /*
     * 입력값을 프로그램 내부에서 사용하는 메뉴 인덱스 값으로 변환
     * */
    private static int toMenuIndex(int no) {
        return no - 1;
    }

    /*
     * 프로그램 내부의 메뉴 인덱스 값을 고객에게 보여지는 메뉴 넘버로 변환
     * */
    private static int toMenuNo(int i) {
        return i + 1;
    }
}

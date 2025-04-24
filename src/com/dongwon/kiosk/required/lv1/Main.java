package com.dongwon.kiosk.required.lv1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] menus = new String[5];
        // 직관성을 위해 0번 인덱스는 비워 놓음.
        menus[1] = "1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거";
        menus[2] = "2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거";
        menus[3] = "3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거";
        menus[4] = "4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거";

        while (true) {
            System.out.println("[ SHAKESHACK MENU ]");
            for (int i = 1; i < menus.length; i++) {
                System.out.println(menus[i]);
            }
            System.out.println("0. 종료      | 종료");
            System.out.print("숫자를 입력해주세요.(0~4사이 숫자): ");
            int input = Integer.parseInt(sc.nextLine());

            if (1 <= input && input <= 4) System.out.println(menus[input] + "\n");
            else if (input == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else System.out.println("올바르지 않은 입력값입니다 : " + input + "\n");

        }
    }
}
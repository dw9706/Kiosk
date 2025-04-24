package com.dongwon.kiosk.required.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<MenuItem> menus = new ArrayList<>(Arrays.asList(
                new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거")
        ));

        while (true) {
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

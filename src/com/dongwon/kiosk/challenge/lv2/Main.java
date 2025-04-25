package com.dongwon.kiosk.challenge.lv2;


public class Main {
    public static void main(String[] args) {
        Menu burgerMenu = new Menu("Burgers",
                new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거")
        );
        Menu drinksMenu = new Menu("Drinks",
                new MenuItem("Lemonade", 3.9, "레몬주스, 설탕, 물로 만든 수제 레모네이드"),
                new MenuItem("Fifty/Fifty", 3.9, "레몬에이드와 아이스티가 반반 섞인 시원한 음료"),
                new MenuItem("Apple Juice", 2.9, "유기농 사과즙"),
                new MenuItem("Soda", 2.5, "코카콜라·스프라이트 등 탄산음료")
        );
        Menu dessertMenu = new Menu("Desserts",
                new MenuItem("Vanilla Shake", 5.9, "바닐라 아이스크림과 우유를 블렌딩한 쉐이크"),
                new MenuItem("Chocolate Shake", 5.9, "초콜릿 아이스크림과 우유를 블렌딩한 쉐이크"),
                new MenuItem("Chocolate Cookie", 3.0, "따뜻한 초코칩 쿠키"),
                new MenuItem("Frozen Custard", 4.9, "부드러운 프로즌 커스터드 디저트")
        );



        Kiosk kiosk = new Kiosk(burgerMenu,drinksMenu,dessertMenu);

        kiosk.start();
    }
}

package com.dongwon.kiosk.challenge.lv1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    private String category;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Menu(String category, MenuItem... menuItems) {
        this.category = category;
        this.menuItems.addAll(Arrays.asList(menuItems));
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}

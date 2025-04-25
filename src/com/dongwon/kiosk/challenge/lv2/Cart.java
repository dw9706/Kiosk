package com.dongwon.kiosk.challenge.lv2;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<MenuItem> cartItems = new ArrayList<>();

    public void addItem(MenuItem item) {
        cartItems.add(item);
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (MenuItem cartItem : cartItems) {
            total += cartItem.getPrice();
        }
        return total;
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public List<MenuItem> getCartItems() {
        return cartItems;
    }

    public void clear() {
        cartItems.clear();
    }

    public void changeCartItems(List<MenuItem> cartItems) {
        this.cartItems = cartItems;
    }
}

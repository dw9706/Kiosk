package com.dongwon.kiosk.challenge.lv1;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<MenuItem> cartItems = new ArrayList<>();

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
}

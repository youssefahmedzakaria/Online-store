package com.OrderNotifierSystem.OrderNotifierModule.orders.DB;

import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;

import java.util.ArrayList;

public class ProductDB {
    private static final ArrayList<Product> products = new ArrayList<>();

public static ArrayList<Product> getProducts() {
    return products;
}

}

package com.OrderNotifierSystem.OrderNotifierModule.orders.DB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;

import java.util.*;

public class OrderDB {
    private static final ArrayList<Order> orders = new ArrayList<>();
    private static final ArrayList<Product> orderedProducts = new ArrayList<>();
    static final Map<Integer, ArrayList<String>> orderMap = new HashMap<>();


    public static ArrayList<Order> getOrders() {
        return orders;
    }
    public static ArrayList<Product> getOrderedProducts() {
        return orderedProducts;
    }
    public static Map<Integer, ArrayList<String>> getOrderMap() {
        return orderMap;
    }


}

package com.OrderNotifierSystem.OrderNotifierModule.orders.DB;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class OrderDB {
    private static ArrayList<Order> orders = new ArrayList<>();
    static final Map<Integer, Order> ORDERSMAP = new HashMap<>();
//
//    private static final ArrayList<Order> compoundOrders = new ArrayList<>();
    static final Map<Integer, ArrayList<String>> orderMap = new HashMap<>();
    static final Map<Integer, Float> orderTotalCosts = new HashMap<>();
    static final Map<Integer, Float> CompoundOrderTotalCosts = new HashMap<>();

    static  Map<Integer, ArrayList<Product>> OrderedProducts = new HashMap<>();

    static final Map<Integer, ArrayList<Order>> CompoundOrders = new HashMap<>();


public static Map<Integer, ArrayList<Order>> getCompoundOrders() {
        return CompoundOrders;
    }
    public static Map<Integer, ArrayList<Product>> getOrderedProducts() {
        return OrderedProducts;
    }

    public  ArrayList<Order> getOrders() {
        return orders;
    }
    public static Map<Integer, ArrayList<String>> getOrderMap() {
        return orderMap;
    }
    public static Map<Integer, Float> getOrderTotalCost() {
        return orderTotalCosts;
    }
    public static Map<Integer, Float> getCompoundOrderTotalCosts() {
        return CompoundOrderTotalCosts;
    }

//    public static ArrayList<Order> getCompoundOrder() {
//        return compoundOrders;
//    }

    public Order getOrder(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }
    public boolean findOrder(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return true;
            }
        }
        return false;
    }
    public boolean findCompoundOrder(int orderId) {

        for (Map.Entry<Integer, ArrayList<Order>> entry : CompoundOrders.entrySet()) {
            if (entry.getKey() == orderId) {
                return true;
            }
        }
        return false;
    }



    public static Map<Integer, Order> getOrdersMap() {
       return ORDERSMAP;
      }



}

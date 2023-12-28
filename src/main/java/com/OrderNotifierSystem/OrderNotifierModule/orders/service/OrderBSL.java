package com.OrderNotifierSystem.OrderNotifierModule.orders.service;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class OrderBSL {
    @Autowired
    private Order order = new Order();
    private final ArrayList<Order> orders = new ArrayList<>();
    private final ArrayList<Product> orderedProducts = new ArrayList<>();

    public ArrayList<Product> getOrderedProducts() {
        return orderedProducts;
    }


    public OrderBSL() {
    }

    public String placeOrder() {
        orderedProducts.addAll(order.getShoppingCartBSL().getShoppingCart().getCart());
        if (order.getShoppingCartBSL().getShoppingCart().getCart().isEmpty()) {
            return "Cart is empty";
        }
        order.setOrderId(order.getOrderId() + 1);
        order.setStatus(true);
        orders.add(order);
        order.setPlaced(true);
        order.getShoppingCartBSL().clearCart();
        return "Order placed successfully \n " +   "Your Order ID is: " + order.getOrderId();
    }

    public String cancelOrderPlacement(int orderId) {
        if (order.getPlaced()) {
            for (Order order : orders) {
                if (order.getOrderId() == orderId) {
                    order.setStatus(false);
                    return "Order Cancelled";
                }
            }
        }
        return "Order not placed";
    }

    public ArrayList<String> getOrder(int orderId) {
        ArrayList<String> orderDetails = new ArrayList<>();
        for (Product product: orderedProducts) {
            if (order.getOrderId() == orderId) {
                return orderDetails;
            }
        }
        return null;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }


}

package com.OrderNotifierSystem.OrderNotifierModule.orders.service;

import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;

import java.util.ArrayList;

public class SimpleOrderBSL extends OrderBSL{

    public String placeOrder() {
        orderedProducts.clear();
        // orderedProducts.addAll(order.getShoppingCartBSL().getShoppingCart().getCart());
        CopyList(order.getShoppingCartBSL().getShoppingCart().getCart(), orderedProducts);
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
                    orders.remove(order);
                    orderMap.remove(orderId);
                    return "Order Cancelled";
                }
            }
        }
        return "Order not placed";
    }


    public ArrayList<String> getOrder(int orderId) {
        ArrayList<String> orderDetails = orderMap.get(orderId);
        if (orderDetails != null) {
            return orderDetails;
        }
        orderDetails = new ArrayList<>();
        if (order.getOrderId() == orderId) {
            for (Product product: orderedProducts) {
                String orderProduct = "Product Details: " + product.getName() + " x " + product.getQuantity() + " = $" + (product.getPrice() * product.getQuantity());
                orderDetails.add(orderProduct);
            }
            orderMap.put(orderId, orderDetails);
            return orderDetails;
        }
        return null;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

}

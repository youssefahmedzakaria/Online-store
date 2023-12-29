package com.OrderNotifierSystem.OrderNotifierModule.orders.service;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.SimpleOrderImp;
import java.util.ArrayList;
import java.util.List;

public class CompoundOrder {
    private List<Order> individualOrders;
    private SimpleOrderImp orderService;

    public CompoundOrder(SimpleOrderImp orderService) {
        this.individualOrders = new ArrayList<>();
        this.orderService = orderService;
    }

    public void addOrder(Order order) {
        individualOrders.add(order);
    }

    public String placeCompoundOrder() {
        if (individualOrders.isEmpty()) {
            return "No orders to process";
        }

        for (Order order : individualOrders) {
            String username = order.getUser().getUsername();
            int orderId = order.getOrderId();
            String placeOrderResult = orderService.placeOrder(username);

            if (!placeOrderResult.contains("Order placed successfully")) {
                return "Failed to place order for user: " + username;
            }

            // Deduct the balance from each user
            float orderCost = order.getShoppingCartBSL().getShoppingCart().getTotalCost();
            order.getUser().setBalance(order.getUser().getBalance() - orderCost);
        }
        return "Compound order placed successfully";
    }

    public String cancelCompoundOrder() {
        for (Order order : individualOrders) {
            String username = order.getUser().getUsername();
            int orderId = order.getOrderId();
            String cancelOrderResult = orderService.cancelOrderPlacement(username, orderId);

            if (!cancelOrderResult.contains("Order Cancelled")) {
                return "Failed to cancel order for user: " + username;
            }

            // Refund the balance to each user
            float orderCost = order.getShoppingCartBSL().getShoppingCart().getTotalCost();
            order.getUser().setBalance(order.getUser().getBalance() + orderCost);
        }
        return "Compound order cancelled successfully";
    }

    // Implement shipping logic similar to placeOrder and cancelOrder
    public String shipCompoundOrder() {
        // Your shipping logic here
    }
}
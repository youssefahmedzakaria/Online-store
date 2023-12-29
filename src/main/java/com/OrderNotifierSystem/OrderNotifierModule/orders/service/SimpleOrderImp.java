package com.OrderNotifierSystem.OrderNotifierModule.orders.service;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class SimpleOrderImp extends OrderImp {
    private static final int CANCELLATION_DURATION_LIMIT = 2;
    @Override
    public String placeOrder() {
        orderDB.getOrderedProducts().clear();
        // orderedProducts.addAll(order.getShoppingCartBSL().getShoppingCart().getCart());
        CopyList(order.getShoppingCartBSL().getShoppingCart().getCart(), orderDB.getOrderedProducts());
        if (order.getShoppingCartBSL().getShoppingCart().getCart().isEmpty()) {
            return "Cart is empty";
        }
        order.setOrderId(order.getOrderId() + 1);
        order.setStatus(true);
        orderDB.getOrders().add(order);
        order.setPlaced(true);
        return "Order placed successfully \n " + "Your Order ID is: " + order.getOrderId();
    }
    @Override
    public String cancelOrderPlacement(int orderId) {
        if (order.getPlaced()) {
            for (Order order : orderDB.getOrders()) {
                if (order.getOrderId() == orderId) {
                    order.setPlaced(false);
                    orderDB.getOrderMap().remove(orderId, orderDB.getOrderMap().get(orderId));
                    order.setStatus(false);
                    return "Order Cancelled";
                }
            }
        }
        return "Order not placed";
    }
    @Override
    public String shipOrder(int orderId) {
        if (order.getPlaced()) {
            for (Order order : orderDB.getOrders()) {
                if (order.getOrderId() == orderId) {
                    order.setShipped(true);
                    order.setPlacementTime(LocalDateTime.now());
                    return "Order Shipped";
                }
            }
        }
        return "Order not placed";
    }

    public String cancelOrderShipping(int orderId) {
        if (order.getShipped()) {
            for (Order order : orderDB.getOrders()) {
                if (order.getOrderId() == orderId) {
                    LocalDateTime currentTime = LocalDateTime.now();

                    LocalDateTime orderPlacementTime = order.getPlacementTime();

                    Duration duration = Duration.between(orderPlacementTime, currentTime);

                    if (duration.toMinutes() <= CANCELLATION_DURATION_LIMIT) {
                        order.setShipped(false);
                        return "Shipping Cancelled";
                    } else {
                        return "Shipment Cancellation period has expired";
                    }
                }
            }
        }
        return "Order not placed";
    }

    public ArrayList<String> getOrder(int orderId) {
        if (!order.getStatus()) {
            return null;
        }
        ArrayList<String> orderDetails = orderDB.getOrderMap().get(orderId);
        if (orderDetails != null) {
            return orderDetails;
        }
        orderDetails = new ArrayList<>();
        if (order.getOrderId() == orderId) {
            for (Product product :  orderDB.getOrderedProducts()) {
                String orderProduct = "Product Details: " + product.getName() + " x " + product.getQuantity() + " = $" + (product.getPrice() * product.getQuantity());
                orderDetails.add(orderProduct);
            }
            orderDB.getOrderMap().put(orderId, orderDetails);
            return orderDetails;
        }
        return null;
    }

    public ArrayList<Order> getOrders() {
        return orderDB.getOrders();
    }

    public String checkOut(int orderId) {
        if (order.getPlaced()) {
            for (Order order : orderDB.getOrders()) {
                if (order.getOrderId() == orderId) {
                    order.setShipped(true);
                    String cost = String.valueOf(order.getShoppingCartBSL().getShoppingCart().getTotalCost());
                    order.getShoppingCartBSL().clearCart();
                    return "Total Cost: " + "$" + cost;
                }
            }
        }
        return "Order not placed";
    }


}

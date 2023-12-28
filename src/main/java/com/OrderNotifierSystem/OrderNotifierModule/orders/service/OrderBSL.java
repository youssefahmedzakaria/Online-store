package com.OrderNotifierSystem.OrderNotifierModule.orders.service;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class OrderBSL {
    @Autowired
    private Order order;
    List<Map<String, Object>> selectedAttributesList = new ArrayList<>();
    private final ArrayList<Order> orders = new ArrayList<>();
    public OrderBSL() {
    }

    public String placeOrder(String productName, int Quantity) {
        ProductBSL productBSL = new ProductBSL();
        Product product = productBSL.findProduct(productName);
        if (product.getName() != null) {
            if (product.getQuantity() >= Quantity) {
                order.getOrder().add(product);
                if (order.getOrder() == null) {
                    order.setTotalCost((float) (product.getPrice() * Quantity));
                    product.setQuantity(product.getQuantity() - Quantity);
                } else {
                    order.setTotalCost((float) (order.getTotalCost() + product.getPrice() * Quantity));
                    product.setQuantity(product.getQuantity() - Quantity);
                }
            } else {
                return "Quantity not available";
            }
        } else {
            return "Product not found";
        }
        Map<String, Object> specs = new HashMap<>();
        specs.put("name", productName);
        specs.put("quantity", Quantity);
        specs.put("totalPrice", (float) (product.getPrice() * Quantity));
        selectedAttributesList.add(specs);

        order.setOrderId(orders.size());
        order.getUser().getOrders().add(order);
        orders.add(order);
        order.setStatus(true);

        return "Order Placed";
    }

    public String cancelOrder(int orderId) {
        for (Order order : order.getUser().getOrders()) {
            if (order.getOrderId() == orderId) {
                order.getUser().getOrders().remove(order);
                order.setStatus(false);
                return "Order Cancelled";
            }
        }
        return "Order not found";
    }


    public ArrayList<Product> getOrder(int orderId) {
        for (Order order : order.getUser().getOrders()) {
            if (order.getOrderId() == orderId) {
                return order.getOrder();
            }
        }
        return null;
    }

    public List<Map<String, Object>> getOrders() {
        return selectedAttributesList;
    }

}

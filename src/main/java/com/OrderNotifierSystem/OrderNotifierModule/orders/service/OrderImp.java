package com.OrderNotifierSystem.OrderNotifierModule.orders.service;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.OrderDB;
import java.util.*;

@Service
public abstract class OrderImp {

    @Autowired
    protected Order order = new Order();

    protected OrderDB orderDB = new OrderDB();

    public void CopyList(ArrayList<Product> list1, ArrayList<Product> list2) {
        for (Product product : list1) {
            list2.add(product);
        }
    }

    public OrderImp() {
    }

    public abstract String placeOrder(String username);

    public abstract String cancelOrderPlacement(String username, int orderId);
    public abstract String shipOrder(String username, int orderId);

    public abstract String cancelOrderShipping(String username, int orderId);

    public abstract ArrayList<String> getOrder(int orderId);

    public abstract ArrayList<Order> getOrders();

    public abstract String checkOut(int orderId);

}

package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.OrderBSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OrderController {
    @Autowired
    private OrderBSL orderBSL;

    public OrderController(OrderBSL orderBSL) {
        this.orderBSL = orderBSL;
    }


    @PostMapping ("/placeOrder")
    public String placeOrder() {
        return orderBSL.placeOrder();
    }

    @PostMapping("/cancelOrder/{orderId}")
    public String cancelOrder(@PathVariable("orderId") int orderId) {
        return orderBSL.cancelOrderPlacement(orderId);
    }

    @GetMapping("/getOrders")
    public ArrayList<Order> getOrders() {
        return orderBSL.getOrders();
    }

    @GetMapping("/getOrder/{orderId}")
    public ArrayList<String> getOrder(@PathVariable("orderId") int orderId) {
        return orderBSL.getOrder(orderId);
    }

    @GetMapping("/checkout/{orderId}")
    public ResponseEntity<String> checkout(@PathVariable("orderId") int orderId) {
        return ResponseEntity.ok(orderBSL.checkOut(orderId));
    }

}

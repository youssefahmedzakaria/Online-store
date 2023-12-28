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


    @PostMapping ("/placeOrder/{productName}/{quantity}")
    public String placeOrder(@PathVariable("productName") String productName, @PathVariable("quantity") int quantity) {
        return orderBSL.placeOrder(productName, quantity);
    }

    @PostMapping("/cancelOrder/{orderId}")
    public String cancelOrder(@PathVariable("orderId") int orderId) {
        return orderBSL.cancelOrder(orderId);
    }

    @GetMapping("/getOrders")
    public List<Map<String, Object>> getOrders() {
        return orderBSL.getOrders();
    }

    @GetMapping("/getOrder/{orderId}")
    public ArrayList<Product> getOrder(@PathVariable("orderId") int orderId) {
        return orderBSL.getOrder(orderId);
    }

}

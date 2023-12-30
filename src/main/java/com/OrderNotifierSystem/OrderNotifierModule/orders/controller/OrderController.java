package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.OrderImp;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.SimpleOrderImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OrderController {

    private final OrderImp orderBSL;
    public OrderController(OrderImp orderBSL) {
        this.orderBSL = orderBSL;
    }


    @GetMapping ("/placeOrder/{username}")
    public String placeOrder(@PathVariable ("username") String username) {
        return orderBSL.placeOrder(username);
    }

    @GetMapping("/cancelOrder/{username}/{orderId}")
    public String cancelOrder(@PathVariable("orderId") int orderId, @PathVariable("username") String username) {
        return orderBSL.cancelOrderPlacement(username ,orderId);
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
    @GetMapping("/shipOrder/{username}/{orderId}")
    public String shipOrder(@PathVariable("orderId") int orderId, @PathVariable("username") String username) {
        return orderBSL.shipOrder(username, orderId);
    }
    @GetMapping("/cancelOrderShipping/{username}/{orderId}")
    public String cancelOrderShipping(@PathVariable("orderId") int orderId, @PathVariable("username") String username) {
        return orderBSL.cancelOrderShipping(username,orderId);
    }
}

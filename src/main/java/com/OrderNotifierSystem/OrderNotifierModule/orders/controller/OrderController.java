package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.OrderImp;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.SimpleOrderImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OrderController {

    @Autowired
    private  OrderImp orderBSL = new SimpleOrderImp();

    public OrderController(OrderImp orderBSL) {
        this.orderBSL = orderBSL;
    }


    @PostMapping ("/placeOrder")
    public String placeOrder() {
        return orderBSL.placeOrder();
    }

    @GetMapping("/cancelOrder/{orderId}")
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
    @GetMapping("/shipOrder/{orderId}")
    public String shipOrder(@PathVariable("orderId") int orderId) {
        return orderBSL.shipOrder(orderId);
    }
    @GetMapping("/cancelOrderShipping/{orderId}")
    public String cancelOrderShipping(@PathVariable("orderId") int orderId) {
        return orderBSL.cancelOrderShipping(orderId);
    }
}

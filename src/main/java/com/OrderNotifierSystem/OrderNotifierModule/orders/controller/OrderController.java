package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.CancelPlacementTemplate;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.CancelShipmentTemplate;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.OrderPlacementTemplate;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.OrderShipmentTemplate;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.CompoundOrder;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Order;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.OrderBSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OrderController {
    @Autowired
    private final OrderBSL orderBSL;
    public OrderController(OrderBSL orderBSL) {
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
    @RequestMapping(value = "/placeOrders", method = RequestMethod.POST)
    public String placeOrders(@RequestParam ("usernames") ArrayList<String> usernames){
        String respons = orderBSL.placeOrders(usernames);
        return respons;
    }

    @GetMapping("/getOrders")
    public ArrayList<String> getOrders() {
        return orderBSL.getOrders();
    }

    @GetMapping("/getOrder/{orderId}/{username}")
    public ArrayList<String> getOrder(@PathVariable("orderId") int orderId, @PathVariable("username") String username) {
        return orderBSL.getOrder(orderId,username);
    }

    @GetMapping("/checkout/{username}/{orderId}")
    public ResponseEntity<String> checkout( @PathVariable("username") String username ,@PathVariable("orderId") int orderId) {
        return ResponseEntity.ok(orderBSL.checkOut(username,orderId));
    }
    @GetMapping("/shipOrder/{username}/{orderId}")
    public String shipOrder(@PathVariable("orderId") int orderId, @PathVariable("username") String username) {
        return orderBSL.shipOrder(username, orderId);
    }
    @GetMapping("/cancelOrderShipping/{username}/{orderId}")
    public String cancelOrderShipping(@PathVariable("orderId") int orderId, @PathVariable("username") String username) {
        return orderBSL.cancelOrderShipping(username,orderId);
    }
    @GetMapping("/cancelCompoundOrder/{orderId}")
    public String CancelOrdersPlacements(@PathVariable("orderId") int orderId) {
        return orderBSL.CancelOrdersPlacements(orderId);
    }
    @GetMapping("/ShipCompoundOrder/{orderId}")
    public String ShipCompoundOrder(@PathVariable("orderId") int orderId) {
        return orderBSL.ShipCompoundOrder(orderId);
    }
    @GetMapping("/cancelCompoundShipping/{orderId}")
    public String CancelOrderCompoundShipment(@PathVariable("orderId") int orderId) {
        return orderBSL.CancelOrderCompoundShipment(orderId);
    }
    @GetMapping("/getCompoundOrder/{orderId}")
    public ArrayList<String> getCompoundOrder(@PathVariable("orderId") int orderId) {
        return orderBSL.getCompoundOrder(orderId);
    }
    @GetMapping("/CheckoutCompoundOrder/{orderId}")
    public String CompoundOrderCheckout(@PathVariable("orderId") int orderId) {
        return orderBSL.CompoundOrderCheckout(orderId);
    }
}
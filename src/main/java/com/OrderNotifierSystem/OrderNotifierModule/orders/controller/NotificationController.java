package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;

import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.CancelPlacementTemplate;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.CancelShipmentTemplate;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.OrderPlacementTemplate;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.OrderShipmentTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NotificationController {
    private CancelShipmentTemplate cancelShipmentTemplate;
    private CancelPlacementTemplate cancelPlacementTemplate;
    private OrderPlacementTemplate orderPlacementTemplate;
    private OrderShipmentTemplate orderShipmentTemplate;

    public NotificationController(){
        cancelShipmentTemplate = new CancelShipmentTemplate();
        cancelPlacementTemplate = new CancelPlacementTemplate();
        orderPlacementTemplate = new OrderPlacementTemplate();
        orderShipmentTemplate = new OrderShipmentTemplate();
    }

    @GetMapping("/sendPlacementNotificationAr/{username}/{email}")
    public String sendOrderPlacementByEmail(@PathVariable("username") String username, @PathVariable("email") String email) {
        return orderPlacementTemplate.sendOrderPlacementByEmail(username,email);
    }
    @GetMapping("/sendPlacementNotificationAr/{username}/{email}/{phoneNumber}")
    public String sendOrderPlacementByEmailAndSMS(@PathVariable("username") String username, @PathVariable("email") String email, @PathVariable("phoneNumber") String phoneNumber) {
        return orderPlacementTemplate.sendOrderPlacementByEmailArabic(username,email);
    }






















}

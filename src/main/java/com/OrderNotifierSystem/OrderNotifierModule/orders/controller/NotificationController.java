package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;

import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.CancelPlacementTemplate;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.CancelShipmentTemplate;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.OrderPlacementTemplate;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.OrderShipmentTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.NotificationsQueue;


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
    public String sendOrderPlacementByEmailAndSMS(@PathVariable("username") String username, @PathVariable("email") String email) {
        return orderPlacementTemplate.sendOrderPlacementByEmailArabic(username,email);
    }
    @GetMapping("/sendPlacementNotificationAr/{username}/{phoneNumber}")
    public String sendOrderPlacementBySMSAr(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return orderPlacementTemplate.sendOrderPlacementBySMSArabic(username,phoneNumber);
    }
    @GetMapping("/sendPlacementNotificationEn/{username}/{email}")
    public String sendOrderPlacementByEmailEn(@PathVariable("username") String username, @PathVariable("email") String email) {
        return orderPlacementTemplate.sendOrderPlacementByEmail(username,email);
    }
    @GetMapping("/sendPlacementNotificationEn/{username}/{phoneNumber}")
    public String sendOrderPlacementByEmailAndSMSEn(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return orderPlacementTemplate.sendOrderPlacementBySMS(username,phoneNumber);
    }
    @GetMapping("/sendShipmentNotificationAr/{username}/{email}")
    public String sendOrderShipmentByEmailAr(@PathVariable("username") String username, @PathVariable("email") String email) {
        return orderShipmentTemplate.sendOrderShipmentByEmailArabic(username,email);
    }
    @GetMapping("/sendShipmentNotificationAr/{username}/{phoneNumber}")
    public String sendOrderShipmentByEmailAndSMSAr(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return orderShipmentTemplate.sendOrderShipmentBySMSArabic(username,phoneNumber);
    }
    @GetMapping("/sendShipmentNotificationEn/{username}/{email}")
    public String sendOrderShipmentByEmailEn(@PathVariable("username") String username, @PathVariable("email") String email) {
        return orderShipmentTemplate.sendOrderShipmentByEmail(username,email);
    }
    @GetMapping("/sendShipmentNotificationEn/{username}/{phoneNumber}")
    public String sendOrderShipmentByEmailAndSMSEn(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return orderShipmentTemplate.sendOrderShipmentBySMS(username,phoneNumber);
    }
    @GetMapping("/sendCancelPlacementNotificationAr/{username}/{email}")
    public String sendCancelPlacementByEmailAr(@PathVariable("username") String username, @PathVariable("email") String email) {
        return cancelPlacementTemplate.sendCancelPlacementByEmailArabic(username,email);
    }
    @GetMapping("/sendCancelPlacementNotificationAr/{username}/{phoneNumber}")
    public String sendCancelPlacementByEmailAndSMSAr(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return cancelPlacementTemplate.sendCancelPlacementBySMSArabic(username, phoneNumber );
    }
    @GetMapping("/sendCancelPlacementNotificationEn/{username}/{email}")
    public String sendCancelPlacementByEmailEn(@PathVariable("username") String username, @PathVariable("email") String email) {
        return cancelPlacementTemplate.sendCancelPlacementByEmail(username,email);
    }

    @GetMapping("/sendCancelPlacementNotificationEn/{username}/{phoneNumber}")
    public String sendCancelPlacementByEmailAndSMSEn(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return cancelPlacementTemplate.sendCancelPlacementBySMS(username, phoneNumber );
    }
    @GetMapping("/sendCancelShipmentNotificationAr/{username}/{email}")
    public String sendCancelShipmentByEmailAr(@PathVariable("username") String username, @PathVariable("email") String email) {
        return cancelShipmentTemplate.sendCancelShipmentByEmailArabic(username,email);
    }
    @GetMapping("/sendCancelShipmentNotificationAr/{username}/{phoneNumber}")
    public String sendCancelShipmentByEmailAndSMSAr(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return cancelShipmentTemplate.sendCancelShipmentBySMSArabic(username, phoneNumber );
    }
    @GetMapping("/sendCancelShipmentNotificationEn/{username}/{email}")
    public String sendCancelShipmentByEmailEn(@PathVariable("username") String username, @PathVariable("email") String email) {
        return cancelShipmentTemplate.sendCancelShipmentByEmail(username,email);
    }
    @GetMapping("/sendCancelShipmentNotificationEn/{username}/{phoneNumber}")
    public String sendCancelShipmentByEmailAndSMSEn(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return cancelShipmentTemplate.sendCancelShipmentBySMS(username, phoneNumber );
    }
}

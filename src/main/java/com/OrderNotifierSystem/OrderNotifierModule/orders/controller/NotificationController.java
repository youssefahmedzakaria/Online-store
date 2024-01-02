package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;

import com.OrderNotifierSystem.OrderNotifierModule.orders.Notfications.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NotificationController {
    private CancelShipmentTemplate cancelShipmentTemplate;
    private CancelPlacementTemplate cancelPlacementTemplate;
    private OrderPlacementTemplate orderPlacementTemplate;
    private OrderShipmentTemplate orderShipmentTemplate;

    private NotificationTemplate notificationTemplate = new NotificationTemplate();

    public NotificationController() {
        cancelShipmentTemplate = new CancelShipmentTemplate();
        cancelPlacementTemplate = new CancelPlacementTemplate();
        orderPlacementTemplate = new OrderPlacementTemplate();
        orderShipmentTemplate = new OrderShipmentTemplate();

    }

    @GetMapping("/sendPlacementNotificationEmailAr/{username}/{email}")
    public String sendOrderPlacementByEmailAndSMS(@PathVariable("username") String username, @PathVariable("email") String email) {
        return orderPlacementTemplate.sendOrderPlacementByEmailArabic(username, email);
    }

    @GetMapping("/sendPlacementNotificationSmsAr/{username}/{phoneNumber}")
    public String sendOrderPlacementBySMSAr(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return orderPlacementTemplate.sendOrderPlacementBySMSArabic(username, phoneNumber);
    }

    @GetMapping("/sendPlacementNotificationEmailEn/{username}/{email}")
    public String sendOrderPlacementByEmailEn(@PathVariable("username") String username, @PathVariable("email") String email) {
        return orderPlacementTemplate.sendOrderPlacementByEmail(username, email);
    }

    @GetMapping("/sendPlacementNotificationSmsEn/{username}/{phoneNumber}")
    public String sendOrderPlacementByEmailAndSMSEn(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return orderPlacementTemplate.sendOrderPlacementBySMS(username, phoneNumber);
    }

    @GetMapping("/sendShipmentNotificationEmailAr/{username}/{email}")
    public String sendOrderShipmentByEmailAr(@PathVariable("username") String username, @PathVariable("email") String email) {
        return orderShipmentTemplate.sendOrderShipmentByEmailArabic(username, email);
    }

    @GetMapping("/sendShipmentNotificationSmsAr/{username}/{phoneNumber}")
    public String sendOrderShipmentByEmailAndSMSAr(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return orderShipmentTemplate.sendOrderShipmentBySMSArabic(username, phoneNumber);
    }

    @GetMapping("/sendShipmentNotificationEmailEn/{username}/{email}")
    public String sendOrderShipmentByEmailEn(@PathVariable("username") String username, @PathVariable("email") String email) {
        return orderShipmentTemplate.sendOrderShipmentByEmail(username, email);
    }

    @GetMapping("/sendShipmentNotificationSmsEn/{username}/{phoneNumber}")
    public String sendOrderShipmentByEmailAndSMSEn(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return orderShipmentTemplate.sendOrderShipmentBySMS(username, phoneNumber);
    }

    @GetMapping("/sendCancelPlacementNotificationEmailAr/{username}/{email}")
    public String sendCancelPlacementByEmailAr(@PathVariable("username") String username, @PathVariable("email") String email) {
        return cancelPlacementTemplate.sendCancelPlacementByEmailArabic(username, email);
    }

    @GetMapping("/sendCancelPlacementNotificationSmsAr/{username}/{phoneNumber}")
    public String sendCancelPlacementByEmailAndSMSAr(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return cancelPlacementTemplate.sendCancelPlacementBySMSArabic(username, phoneNumber);
    }

    @GetMapping("/sendCancelPlacementNotificationEmailEn/{username}/{email}")
    public String sendCancelPlacementByEmailEn(@PathVariable("username") String username, @PathVariable("email") String email) {
        return cancelPlacementTemplate.sendCancelPlacementByEmail(username, email);
    }

    @GetMapping("/sendCancelPlacementNotificationSmsEn/{username}/{phoneNumber}")
    public String sendCancelPlacementByEmailAndSMSEn(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return cancelPlacementTemplate.sendCancelPlacementBySMS(username, phoneNumber);
    }

    @GetMapping("/sendCancelShipmentNotificationEmailAr/{username}/{email}")
    public String sendCancelShipmentByEmailAr(@PathVariable("username") String username, @PathVariable("email") String email) {
        return cancelShipmentTemplate.sendCancelShipmentByEmailArabic(username, email);
    }

    @GetMapping("/sendCancelShipmentNotificationSmsAr/{username}/{phoneNumber}")
    public String sendCancelShipmentByEmailAndSMSAr(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return cancelShipmentTemplate.sendCancelShipmentBySMSArabic(username, phoneNumber);
    }

    @GetMapping("/sendCancelShipmentNotificationEmailEn/{username}/{email}")
    public String sendCancelShipmentByEmailEn(@PathVariable("username") String username, @PathVariable("email") String email) {
        return cancelShipmentTemplate.sendCancelShipmentByEmail(username, email);
    }

    @GetMapping("/sendCancelShipmentNotificationSmsEn/{username}/{phoneNumber}")
    public String sendCancelShipmentByEmailAndSMSEn(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber) {
        return cancelShipmentTemplate.sendCancelShipmentBySMS(username, phoneNumber);
    }

    @GetMapping("/getMostUsedTemplate")
    public String getMostUsedTemplate() {
        return notificationTemplate.maxNotification();
    }
}

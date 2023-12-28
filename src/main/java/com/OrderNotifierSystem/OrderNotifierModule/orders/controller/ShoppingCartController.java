package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;

import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.ShoppingCartBSL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ShoppingCartController {

    private ShoppingCartBSL shoppingCartBSL = new ShoppingCartBSL();

    @PostMapping("/addToCart/{productName}/{quantity}")
    public String addToCart(@PathVariable("productName") String productName, @PathVariable("quantity") int quantity) {
        return shoppingCartBSL.addToCart(productName, quantity);
    }
    @PostMapping("/removeProduct/{productName}")
    public String removeProduct(@PathVariable("productName") String productName) {
        return shoppingCartBSL.removeProduct(productName);
    }

    @GetMapping("/displayCart")
    public ResponseEntity <ArrayList<String>> displayCart() {
        return ResponseEntity.ok(shoppingCartBSL.DisplayCart());
    }

}

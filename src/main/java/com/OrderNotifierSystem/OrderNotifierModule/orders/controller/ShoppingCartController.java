package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;

import com.OrderNotifierSystem.OrderNotifierModule.orders.service.ShoppingCartImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ShoppingCartController {

    private ShoppingCartImp shoppingCartBSL = new ShoppingCartImp();

    @GetMapping("/addToCart/{username}/{productName}/{quantity}")
    public String addToCart( @PathVariable("username") String username, @PathVariable("productName") String productName, @PathVariable("quantity") int quantity) {
        return shoppingCartBSL.addToCart(username,productName, quantity);
    }
    @DeleteMapping("/removeProduct/{username}/{productName}")
    public String removeProduct(@PathVariable("username") String username, @PathVariable("productName") String productName) {
        return shoppingCartBSL.removeFromCart(username,productName);
    }
    @GetMapping("/displayCart/{username}")
        public ResponseEntity <ArrayList<String>> displayCart(@PathVariable("username") String username) {
        return ResponseEntity.ok(shoppingCartBSL.DisplayCart(username));
    }

}

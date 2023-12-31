package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;

import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.ShoppingCartImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class ShoppingCartController {
    ShoppingCartImp shoppingCartImp;
    User user = new User();
    public ShoppingCartController(){
        shoppingCartImp = new ShoppingCartImp();
    }
    @GetMapping("/addToCart/{username}/{productName}/{quantity}")
    public String addToCart( @PathVariable("username") String username, @PathVariable("productName") String productName, @PathVariable("quantity") int quantity) {
        //return user.addToCart(username,productName, quantity);
        return shoppingCartImp.addToCart(username,productName, quantity);
    }
    @DeleteMapping("/removeProduct/{username}/{productName}")
    public String removeProduct(@PathVariable("username") String username, @PathVariable("productName") String productName) {
        return user.getShoppingCart().removeFromCart(username,productName);
    }
    @GetMapping("/displayCart/{username}")
        public ResponseEntity <ArrayList<String>> displayCart(@PathVariable("username") String username) {
        return ResponseEntity.ok(user.getShoppingCart().DisplayCart(username));
    }

    public User getUser() {
        return user;
    }
}

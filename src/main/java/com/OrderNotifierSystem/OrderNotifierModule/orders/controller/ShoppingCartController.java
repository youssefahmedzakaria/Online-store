package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.ShoppingCart;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.ShoppingCartBSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController {
    @Autowired
    private ShoppingCartBSL shoppingCartBSL;
    public ShoppingCartController(ShoppingCartBSL shoppingCartBSL) {
        shoppingCartBSL = shoppingCartBSL;
    }

    @PostMapping(value = "/addToCart")
    public ResponseEntity<String> addToCart(@RequestBody ShoppingCart s) {
        shoppingCartBSL.addToCart(s.getUser(), s.getCart().get(0));
        return ResponseEntity.ok("Added to cart");
    }

}

package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.ProductDBImp;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@AllArgsConstructor
@RestController
public class ProductController {

    private final ProductDBImp ProductBSL;
    @GetMapping(value = "/makeMenu")
    public ResponseEntity makeMenu() {
        return ResponseEntity.ok(ProductBSL.makeMenu());
    }

    @GetMapping(value = "/products")
    public ResponseEntity<ArrayList<Product>> ViewProducts() {
        return ResponseEntity.ok(ProductBSL.ViewProducts());
    }

    @GetMapping(value = "/getProduct/{name}")
    public ResponseEntity<Product> searchProduct(@PathVariable("name") String name) {
        return ResponseEntity.ok(ProductBSL.findProduct(name));
    }

    @PostMapping(value = "/addProduct")
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(ProductBSL.addProduct(product));

    }

    @DeleteMapping(value = "deleteProduct/{name}")
    public ResponseEntity<String> deleteProduct(@PathVariable("name") String name) {
        return ResponseEntity.ok(ProductBSL.deleteProduct(name));
    }

}
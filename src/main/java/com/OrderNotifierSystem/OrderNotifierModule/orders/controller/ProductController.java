package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.ProductBSL;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@AllArgsConstructor
@RestController
public class ProductController {

    private final ProductBSL ProductBSL;

    @GetMapping(value = "/products")
    public ResponseEntity<ArrayList<Product>> getProducts() {
        return ResponseEntity.ok(ProductBSL.getProducts());
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
package com.OrderNotifierSystem.OrderNotifierModule.orders.service;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import com.OrderNotifierSystem.OrderNotifierModule.orders.DB.ProductDB;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ProductDBImp {
    private final ProductDB productDB = new ProductDB();
    public ProductDBImp() {

    }


    public ArrayList<Product> ViewProducts() {
        return productDB.getProducts();
    }

    public Product findProduct(String name) {
        for (Product product : productDB.getProducts()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public String addProduct(Product product) {
        for (Product product1 : productDB.getProducts()) {
            if (product1.getSerialNumber().equalsIgnoreCase(product.getSerialNumber())) {
                return "Product already exists";
            }
        }
        productDB.getProducts().add(product);
        return "Product added successfully";
    }
    public String makeMenu(){
        productDB.getProducts().add(new Product("1", "Apple", "المعلم زكريا", "Fruit", 10.0, 100));
        productDB.getProducts().add(new Product("2", "Orange", "المعلم زكريا", "Fruit", 20.0, 100));
        productDB.getProducts().add(new Product("3", "Mango", "المعلم زكريا", "Fruit", 30.0, 100));
        productDB.getProducts().add(new Product("4", "Banana", "المعلم زكريا", "Fruit", 40.0, 100));
        productDB.getProducts().add(new Product("5", "Pineapple", "المعلم طارق العتر", "Fruit", 50.0, 100));
        productDB.getProducts().add(new Product("6", "Grapes", "المعلم طارق العتر", "Fruit", 60.0, 100));
        productDB.getProducts().add(new Product("7", "Strawberry", "المعلم طارق العتر", "Fruit", 70.0, 100));
        productDB.getProducts().add(new Product("8", "Watermelon", "المعلمة مرام", "Fruit", 80.0, 100));
        productDB.getProducts().add(new Product("9", "Papaya", "المعلمة مرام", "Fruit", 90.0, 100));
        productDB.getProducts().add(new Product("10", "Guava", "المعلمة مرام", "Fruit", 100.0, 100));
        return "Menu created successfully";
    }
    public String deleteProduct(String name) {
        Product product = findProduct(name);
        if (product == null) {
            return "Product does not exist";
        }
        productDB.getProducts().remove(product);
        return "Product deleted successfully";
    }
}
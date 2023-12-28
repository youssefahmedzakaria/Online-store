package com.OrderNotifierSystem.OrderNotifierModule.orders.service;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Product;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ProductBSL {
private ArrayList<Product> products = new ArrayList<Product>();

    public ProductBSL() {
        products.add(new Product("1", "Apple", "المعلم زكريا", "Fruit", 10.0, 100));
        products.add(new Product("2", "Orange", "المعلم زكريا", "Fruit", 20.0, 100));
        products.add(new Product("3", "Mango", "المعلم زكريا", "Fruit", 30.0, 100));
        products.add(new Product("4", "Banana", "المعلم زكريا", "Fruit", 40.0, 100));
        products.add(new Product("5", "Pineapple", "المعلم طارق العتر", "Fruit", 50.0, 100));
        products.add(new Product("6", "Grapes", "المعلم طارق العتر", "Fruit", 60.0, 100));
        products.add(new Product("7", "Strawberry", "المعلم طارق العتر", "Fruit", 70.0, 100));
        products.add(new Product("8", "Watermelon", "المعلمة مرام", "Fruit", 80.0, 100));
        products.add(new Product("9", "Papaya", "المعلمة مرام", "Fruit", 90.0, 100));
        products.add(new Product("10", "Guava", "المعلمة مرام", "Fruit", 100.0, 100));
    }



    public ArrayList<Product> getProducts() {
        return products;
    }

    public Product findProduct(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public String addProduct(Product product) {
        boolean flag = false;
        for (Product product1 : products) {
            if (product1.getSerialNumber().equalsIgnoreCase(product.getSerialNumber())) {
                flag = true;
                return "Product already exists";
            }
        }
        products.add(product);
        return "Product added successfully";
    }

    public String deleteProduct(String name) {
        Product product = findProduct(name);
        if (product == null) {
            return "Product does not exist";
        }
        products.remove(product);
        return "Product deleted successfully";
    }
}
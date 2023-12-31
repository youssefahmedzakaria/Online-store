package com.OrderNotifierSystem.OrderNotifierModule.orders.model;
import java.util.*;

import com.OrderNotifierSystem.OrderNotifierModule.orders.service.ShoppingCartImp;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.UsersImp;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.OrderNotifierSystem.OrderNotifierModule.orders.service.ShoppingCartImp.productBSL;

@Component
public class User {

    private final ArrayList<Order> orders;
    private final ShoppingCartImp shoppingCartimp ;
    private  final ArrayList<Product> orderedProducts;
    //to be removed
    private String username;
    private String email;
    private String password;
    private String address;
    private float balance;
    private String phone;
    private Order currentorder;

    public User() {
        shoppingCartimp = new ShoppingCartImp();
        orders = new ArrayList<>();
        orderedProducts = new ArrayList<>();
        currentorder = new Order();
    }


    public ArrayList<Product> getCurrentordered(){
        return orderedProducts;
    }
    public Order getCurrentorder(){
        return currentorder;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
    public ShoppingCartImp getShoppingCart() {
        return shoppingCartimp;
    }

    public void setCurrentorder(Order currentorder) {
        this.currentorder = currentorder;
    }
}

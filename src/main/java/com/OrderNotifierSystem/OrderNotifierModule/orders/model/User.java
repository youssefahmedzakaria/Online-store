package com.OrderNotifierSystem.OrderNotifierModule.orders.model;
import java.util.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {
    private ArrayList<Order> orders = new ArrayList<>();
    private String username;
    private String email;
    private String password;
    private String address;
    private float balance;
    private String phone;


    public User() {
    }

    public User(ArrayList<Order> orders, String username, String email, String password, String address, float balance, String phone) {
        this.orders = orders;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.balance = balance;
        this.phone = phone;
    }
    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
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

}

package com.OrderNotifierSystem.OrderNotifierModule.orders.model;
import java.util.*;
public class User {
    private String email;
    private String username;
    private String password;
    private double balance;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance() {
        double balance = 10000 + new Random().nextDouble() * 10000;
        this.balance = balance;
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

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

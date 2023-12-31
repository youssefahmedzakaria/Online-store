package com.OrderNotifierSystem.OrderNotifierModule.orders.controller;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.Response;
import com.OrderNotifierSystem.OrderNotifierModule.orders.model.User;
import com.OrderNotifierSystem.OrderNotifierModule.orders.service.UsersImp;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UsersImp usersBSL;

    public UserController(UsersImp usersBSL) {
        this.usersBSL = usersBSL;
    }

    @PostMapping("/addUser")
    public Response addPerson(@RequestBody User p) {
        System.out.println("in add person"+p);
        boolean flag = usersBSL.checkUser(p.getUsername());
        if (flag) {
            Response response = new Response();
            response.setStatus(false);
            response.setMessage("User Already Exists");
            return response;
        }
        usersBSL.createUser(p);
        Response response = new Response();
        response.setStatus(true);
        response.setMessage("User created successfully");
        return response;
    }

    @GetMapping("/get/{username}")
    public String getPerson(@PathVariable("username") String username) {
        return usersBSL.viewUser(username);
    }
}

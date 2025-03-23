package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    //dashboard page
    //by default get request , to make explicitly use reqmap(value="" method="")
    @RequestMapping("/dashboard")
    public String userDashboard()
    {
        System.out.println("user dashboard---------");
        return "UserView/dashboard";
    }

    //user profile page
    @RequestMapping("/profile")
    public String userProfile()
    {
        System.out.println("user Profile -------");
        return "UserView/profile";
    }
}

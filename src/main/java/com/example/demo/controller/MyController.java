package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MyController {
  
    @RequestMapping("/")
    public String indexControl()
    {
        return "index";
    }
    @RequestMapping("/home")
    public String homeControl(Model model)
    {
        return "home";
    }
    @RequestMapping("/about")
    public String myAbout(Model model) {
        System.out.println("this is about controller.");

        //sending data to view
        model.addAttribute("name", "David");
        model.addAttribute("weight", "65");
        return "about";
    }

    @RequestMapping("/service")
    public String serviceControl()
    {
        return "service";
    }
    @RequestMapping("/contact")
    public String contactControl()
    {
        return "contact";
    }
    @RequestMapping("/login")
    public String loginControl()
    {
        return "login";
    }
    @RequestMapping("/signup")
    public String signupControl()
    {
        return "register";
    }
 
    
}

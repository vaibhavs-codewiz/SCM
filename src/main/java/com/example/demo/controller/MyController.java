package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MyController {
  
    @RequestMapping("/about")
    public String myAbout(Model model) {
        System.out.println("this is about controller.");

        //sending data to view
        model.addAttribute("name", "David");
        model.addAttribute("weight", "65");
        
        return "about";
    }
    
}

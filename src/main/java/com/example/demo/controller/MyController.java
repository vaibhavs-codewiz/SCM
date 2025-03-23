package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.MyUser;
import com.example.demo.forms.UserForm;
import com.example.demo.helpers.Message;
import com.example.demo.helpers.MessageType;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class MyController {
    
    @Autowired
    private UserService userService;

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
    public String signupControl(Model model)
    {
        UserForm userForm = new UserForm();
        userForm.setName("");
        userForm.setAbout("");
        userForm.setEmail("");
        userForm.setPhoneNumber("");
        model.addAttribute("userForm",userForm);
        return "register";
    }
    // processing the request
     @PostMapping("/doRegister")
    public String ProcessRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session)
     {
        System.out.println("do register controller executed.............");
        // fetch form data
        System.out.println(userForm.getName());

        //validate form data
           if(rBindingResult.hasErrors())
           return "register";
        //save to db

        //user service
        //    MyUser user = MyUser.builder()
        //    .name(userForm.getName())
        //    .email(userForm.getEmail())
        //    .phoneNum(userForm.getPhoneNumber()) 
        //    .about(userForm.getAbout())
        //    .build();

        MyUser user = new MyUser();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPhoneNum(userForm.getPhoneNumber());
        user.setAbout(userForm.getAbout());
        user.setPassword(userForm.getPassword());

           MyUser savedUser = userService.saveUser(user);
           System.out.println("user saved----------------");
           System.out.println(savedUser);
           Message message = Message.builder().content("user registered successfully this time !!!").type(MessageType.green).build();
           session.setAttribute("message", message);
        //message 
         return "redirect:/signup";
     }
    
}    

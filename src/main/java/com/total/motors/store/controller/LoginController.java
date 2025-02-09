package com.total.motors.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @GetMapping("/")
    public RedirectView redirectToLogin(){
        return new RedirectView("/login");
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}

package com.react.pnld.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping(value = {"/login"})
    public String login(){
        return "login";
    }

    @PostMapping(value = {"/login"})
    public String loginAuth(Authentication authentication){

        return "login";
    }
}

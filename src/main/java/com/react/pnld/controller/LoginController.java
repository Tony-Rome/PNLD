package com.react.pnld.controller;

import com.react.pnld.mappers.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    AdminMapper adminMapper;

    @GetMapping(value = {"/login"})
    public String login(){
        return "login";
    }
}

package com.react.pnld.controller;

import com.react.pnld.mappers.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    AdminMapper adminMapper;

    @GetMapping(value = {"/login"})
    public String login() {
        return "login";
    }
}

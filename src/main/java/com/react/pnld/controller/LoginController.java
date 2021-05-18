package com.react.pnld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class LoginController {

    @ApiIgnore
    @GetMapping(value = {"/login"})
    public String login() {
        return "login";
    }
}

package com.react.pnld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class HomeController {

    @ApiIgnore
    @GetMapping("/home")
    public String home() {
        return "home/home";
    }

}

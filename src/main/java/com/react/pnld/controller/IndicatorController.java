package com.react.pnld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndicatorController {

    @GetMapping("/indicators")
    public String indicatorBase(){ return "indicators/indicator-base"; }
}

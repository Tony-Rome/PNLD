package com.react.pnld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/indicators")
public class IndicatorController {

    @GetMapping("/training")
    public String trainingIndicator(){ return "indicators/training"; }

    @GetMapping("/code")
    public String codeIndicator(){ return "indicators/code"; }

    @GetMapping("/TPC")
    public String tpcIndicator(){ return "indicators/tpc"; }
}

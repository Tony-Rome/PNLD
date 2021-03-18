package com.react.pnld.controller;

import com.react.pnld.model.CsvFile;
import com.react.pnld.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping(value = "/")
    public String index(Model model) {

        return "index";
    }

    @GetMapping(value = "/scheduleLoadFilePost")
    public String scheduleLoadFileGet(Model model) {
       
        return "loadFiles";
    }

    @PostMapping("/scheduleLoadFilePost")
    public String scheduleLoadFilePost(CsvFile csvFile, Model model, @RequestParam("uploadFile") MultipartFile uploadFile) {


        return "loadFiles";
    }
}
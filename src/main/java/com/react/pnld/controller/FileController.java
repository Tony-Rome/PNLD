package com.react.pnld.controller;

import com.react.pnld.model.dto.ScheduleFileLoadDTO;
import com.react.pnld.model.ScheduleFileLoadResponse;
import com.react.pnld.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @GetMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping(value = "/scheduleFileLoadPost")
    public String scheduleFileLoadGet(Model model) {
        logger.debug("scheduleFileLoadGet. form load file is loaded");
        return "loadFiles";
    }

    @PostMapping("/scheduleFileLoadPost")
    public String scheduleFileLoadPost(ScheduleFileLoadDTO scheduleFileLoadDTO, Model model) {
        model.addAttribute("scheduleFileLoadDTO", scheduleFileLoadDTO);
        logger.info("scheduleFileLoadPost. scheduleFileLoadDTO={}", scheduleFileLoadDTO);

        ScheduleFileLoadResponse scheduleFileLoadResponse = fileService.scheduleLoad(scheduleFileLoadDTO);
        logger.info("scheduleLoadFilePost. scheduleFileLoadResponse={}", scheduleFileLoadResponse);

        //TODO in else change to error page
        String response = (scheduleFileLoadResponse.getResponse().equals("OK"))? "loadFiles" : "loadFiles";
        return response;
    }

    @GetMapping("/executeLoadScheduled")
    public String executeLoadScheduled(){
        logger.info("executeLoadScheduled. init");
        fileService.executeFileLoadScheduled();
        return "finish";
    }
}
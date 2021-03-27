package com.react.pnld.controller;

import com.react.pnld.model.dto.ScheduleFileLoadDTO;
import com.react.pnld.model.ScheduleFileLoadResponse;
import com.react.pnld.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Response;
import java.util.Hashtable;
import java.util.Map;

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
    public String scheduleFileLoadGet(Model model) { return "loadFiles"; }

    @PostMapping(value = "/scheduleFileLoadPost")
    public ResponseEntity<String> scheduleFileLoadPost(ScheduleFileLoadDTO scheduleFileLoadDTO, Model model) {

        String loadedBy = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("scheduleFileLoadPost. loadedBy={}",loadedBy);

        model.addAttribute("scheduleFileLoadDTO", scheduleFileLoadDTO);
        logger.info("scheduleFileLoadPost. scheduleFileLoadDTO={}", scheduleFileLoadDTO);

        ScheduleFileLoadResponse scheduleFileLoadResponse = fileService.scheduleLoad(scheduleFileLoadDTO);
        logger.info("scheduleLoadFilePost. scheduleFileLoadResponse={}", scheduleFileLoadResponse);

        return new ResponseEntity<>(scheduleFileLoadResponse.getDescription(), scheduleFileLoadResponse.getResponse());
    }
}
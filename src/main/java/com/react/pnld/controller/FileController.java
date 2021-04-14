package com.react.pnld.controller;

import com.react.pnld.dto.FileTableResumeDTO;
import com.react.pnld.dto.ScheduleFileLoadDTO;
import com.react.pnld.controller.response.ScheduleFileLoadResponse;
import com.react.pnld.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);


    @Autowired
    private FileService fileService;

    @GetMapping(value = "/")
    public String index(Model model) {
        return "filesTable";
    }

    @GetMapping(value = {"/scheduleFileLoadPost","/scheduleFileLoadPost/page/{page}"})
    public ModelAndView scheduleFileLoadGet(@PathVariable(required = false, value = "page") Integer page) {

        List<FileTableResumeDTO> fileTableResumeDTOList = fileService.getUploadedFiles();

        int currentPage = 1;

        if(page != null){
            currentPage = page.intValue();
        }

        ModelAndView mav = new ModelAndView("uploadFile");
        mav.addObject("filesList", fileTableResumeDTOList);
        mav.addObject("currentPage", currentPage);

        return mav;
    }

    @PostMapping(value = "/scheduleFileLoadPost")
    public ResponseEntity<String> scheduleFileLoadPost(ScheduleFileLoadDTO scheduleFileLoadDTO, Model model) {


        model.addAttribute("scheduleFileLoadDTO", scheduleFileLoadDTO);

        String loadedBy = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("scheduleFileLoadPost. loadedBy={}",loadedBy);
        scheduleFileLoadDTO.setLoadedBy(loadedBy);
        logger.info("scheduleFileLoadPost. scheduleFileLoadDTO={}", scheduleFileLoadDTO);

        ScheduleFileLoadResponse scheduleFileLoadResponse = fileService.scheduleLoad(scheduleFileLoadDTO);
        logger.info("scheduleLoadFilePost. scheduleFileLoadResponse={}", scheduleFileLoadResponse);

        return new ResponseEntity<>(scheduleFileLoadResponse.getDescription(), scheduleFileLoadResponse.getResponse());
    }

    @GetMapping("/executeLoadScheduled")
    public String executeLoadScheduled(){
        logger.info("executeLoadScheduled. init");
        fileService.executeFileLoadScheduled();
        return "finish";
    }
}
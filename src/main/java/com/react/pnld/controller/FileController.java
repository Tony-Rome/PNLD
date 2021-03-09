package com.react.pnld.controller;

import com.react.pnld.PnldIndicatorsApplication;
import com.react.pnld.model.CsvFile;
import com.react.pnld.services.FileService;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @GetMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping(value = "/scheduleLoadFilePost")
    public String scheduleLoadFileGet(Model model) {
        logger.debug("scheduleLoadFileGet. form load file is loaded");
        return "loadFiles";
    }

    @PostMapping("/scheduleLoadFilePost")
    public String scheduleLoadFilePost(CsvFile csvFile, Model model, @RequestParam("uploadFile") MultipartFile uploadFile) {
        logger.info("scheduleLoadFilePost. csvFile={}, model={}, uploadFile={}", csvFile, model, uploadFile);

        model.addAttribute("csvFile", csvFile);

        if(uploadFile.isEmpty()){
            logger.debug("uploadFile.isEmpty()");//this if is not necessary with js validation
            return "File is empty";
        }

        csvFile.setUploadFile(uploadFile);
        boolean responseScheduleLoad = fileService.scheduleLoad(csvFile);
        logger.info("scheduleLoadFilePost. responseScheduleLoad={}", responseScheduleLoad);

        return "loadFiles";
    }
}
package com.react.pnld.controller;

import com.react.pnld.controller.response.TrainingInstitutionIndicatorResponse;
import com.react.pnld.dto.TrainingInstitutionIndicatorDTO;
import com.react.pnld.services.TrainingIndicatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private TrainingIndicatorService trainingIndicatorService;

    @GetMapping(value = "/capacitaciones/establecimientos")
    public TrainingInstitutionIndicatorResponse getInfoTrainedInstitutions(@RequestParam(name = "fromYear") int fromYear,
                                                                           @RequestParam(name = "toYear") int toYear){
        logger.info("getInstitutionsTrainingInfo. fromYear={}, toYear={}", fromYear, toYear);

        List<TrainingInstitutionIndicatorDTO> trainingInstitutionIndicatorDTOList;
        trainingInstitutionIndicatorDTOList = trainingIndicatorService.participantInstitutionNumberPNLD(fromYear, toYear);

        System.out.println(trainingInstitutionIndicatorDTOList);

        return new TrainingInstitutionIndicatorResponse(fromYear, toYear, trainingInstitutionIndicatorDTOList);
    }

    @GetMapping(value = "/capacitaciones/docentes")
    public String getInfoTrainedTeacher(@RequestParam(name = "fromYear") int fromYear,
                                        @RequestParam(name = "toYear") int toYear){
        return "Work in progress";
    }

    @GetMapping(value = "/test-pc/docentes")
    public String getInfoTeacherCTTest(@RequestParam(name = "fromYear") int fromYear,
                                        @RequestParam(name = "toYear") int toYear){
        return "Work in progress";
    }

    @GetMapping(value = "/test-pc/estudiantes")
    public String getInfoStudentsCTTest(@RequestParam(name = "fromYear") int fromYear,
                                      @RequestParam(name = "toYear") int toYear){
        return "Work in progress";
    }

}

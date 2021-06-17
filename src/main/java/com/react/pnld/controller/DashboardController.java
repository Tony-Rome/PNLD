package com.react.pnld.controller;

import com.react.pnld.controller.response.TrainingIndicatorResponse;
import com.react.pnld.dto.TrainingIndicatorDTO;
import com.react.pnld.services.TrainingIndicatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private TrainingIndicatorService trainingIndicatorService;

    //TODO: Mapper code.org
    //TODO: Mapper TPC

    @GetMapping(value = "/training/institution")
    public TrainingIndicatorResponse getTrainingInstitutionData(@RequestParam(name = "fromYear") int fromYear,
                                                                @RequestParam(name = "toYear") int toYear) {
        logger.info("getInstitutionsTrainingInfo. fromYear={}, toYear={}", fromYear, toYear);

        List<TrainingIndicatorDTO> trainingIndicatorDTOList;
        trainingIndicatorDTOList = trainingIndicatorService.trainingInstitutionData(fromYear, toYear);

        logger.info("getInstitutionsTrainingInfo. trainingInstitutionIndicatorDTOList={}", trainingIndicatorDTOList);

        return new TrainingIndicatorResponse(trainingIndicatorDTOList);
    }

    @GetMapping(value = "/training/teacher")
    public TrainingIndicatorResponse getTrainingTeacherData(@RequestParam(name = "fromYear") int fromYear,
                                        @RequestParam(name = "toYear") int toYear) {

        logger.info("getTrainingTeacherData. fromYear={}, toYear={}", fromYear, toYear);

        List<TrainingIndicatorDTO> trainingIndicatorDTOList;
        trainingIndicatorDTOList = trainingIndicatorService.trainingTeacherData(fromYear, toYear);

        logger.info("getTrainingTeacherData. trainingTeacherIndicatorDTOList={}", trainingIndicatorDTOList);

        return new TrainingIndicatorResponse(trainingIndicatorDTOList);
    }

    @GetMapping(value = "/code/teacher")
    public String getCodeTeacherData(@RequestParam(name = "fromYear") int fromYear, @RequestParam(name = "toYear") int toYear){
        return "Work in progress";
    }

    @GetMapping(value = "/code/student")
    public String getCodeStudentData(@RequestParam(name = "fromYear") int fromYear, @RequestParam(name = "toYear") int toYear){
        return "Work in progress";
    }

    @GetMapping(value = "/ct-test/teacher")
    public String getInfoTeacherCTTest(@RequestParam(name = "fromYear") int fromYear,
                                       @RequestParam(name = "toYear") int toYear) {
        return "Work in progress";
    }

    @GetMapping(value = "/ct-test/student")
    public String getInfoStudentsCTTest(@RequestParam(name = "fromYear") int fromYear,
                                        @RequestParam(name = "toYear") int toYear) {
        return "Work in progress";
    }

}

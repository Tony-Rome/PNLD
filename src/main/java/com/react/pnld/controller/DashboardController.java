package com.react.pnld.controller;

import com.react.pnld.controller.response.TrainingInfoRegion;
import com.react.pnld.controller.response.InfoTrainedInstitutionsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @GetMapping(value = "/capacitaciones/establecimientos")
    public InfoTrainedInstitutionsResponse getInfoTrainedInstitutions(@RequestParam(name = "fromYear") int fromYear,
                                                                      @RequestParam(name = "toYear") int toYear){
        logger.info("getInstitutionsTrainingInfo. fromYear={}, toYear={}", fromYear, toYear);

        List<TrainingInfoRegion> trainingInfoRegionList = new ArrayList<>();
        trainingInfoRegionList.add(new TrainingInfoRegion(1, "Arica", 5, 0.3f, 0.6f));
        trainingInfoRegionList.add(new TrainingInfoRegion(5, "Valparaiso", 20, 0.6f, 0.3f));

        return new InfoTrainedInstitutionsResponse(fromYear, toYear, trainingInfoRegionList);
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

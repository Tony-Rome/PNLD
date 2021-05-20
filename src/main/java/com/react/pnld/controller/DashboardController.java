package com.react.pnld.controller;

import com.react.pnld.controller.response.RegionTrainingInfo;
import com.react.pnld.controller.response.TrainedInstitutionsResponse;
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
    public TrainedInstitutionsResponse getInfoTrainedInstitutions(@RequestParam(name = "fromYear") int fromYear,
                                                                  @RequestParam(name = "toYear") int toYear){
        logger.info("getInstitutionsTrainingInfo. fromYear={}, toYear={}", fromYear, toYear);

        List<RegionTrainingInfo> regionTrainingInfoList = new ArrayList<>();
        regionTrainingInfoList.add(new RegionTrainingInfo(1, "Arica", 5, 0.3f, 0.6f));
        regionTrainingInfoList.add(new RegionTrainingInfo(1, "Valparaiso", 20, 0.6f, 0.3f));
        return new TrainedInstitutionsResponse();
    }

}

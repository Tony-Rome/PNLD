package com.react.pnld.services;

import com.react.pnld.dto.TrainingInstitutionIndicatorDTO;
import com.react.pnld.repo.TrainingIndicatorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingIndicatorService {

    private static final Logger logger = LoggerFactory.getLogger(TrainingIndicatorService.class);

    @Autowired
    private TrainingIndicatorRepository trainingIndicatorRepository;


    public List<TrainingInstitutionIndicatorDTO> participantInstitutionNumberPNLD(int fromYear, int toYear) {
        return trainingIndicatorRepository.participantInstitutionNumberPNLD(fromYear, toYear);
    }
}

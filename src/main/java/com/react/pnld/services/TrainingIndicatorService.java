package com.react.pnld.services;

import com.react.pnld.dto.TrainingInstitutionIndicatorDTO;
import com.react.pnld.repo.TrainingIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingIndicatorService {

    @Autowired
    private TrainingIndicatorRepository trainingIndicatorRepository;

    public List<TrainingInstitutionIndicatorDTO> participantInstitutionNumberPNLD(int fromYear, int toYear) {
        return trainingIndicatorRepository.participantInstitutionNumberPNLD(fromYear, toYear);
    }
}

package com.react.pnld.services;

import com.react.pnld.dto.TrainingInstitutionIndicatorDTO;
import com.react.pnld.repo.TrainingInstitutionIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingInstitutionIndicatorService {

    @Autowired
    private TrainingInstitutionIndicatorRepository trainingInstitutionIndicatorRepository;

    public List<TrainingInstitutionIndicatorDTO> trainingInstitutionData(int fromYear, int toYear) {
        return trainingInstitutionIndicatorRepository.trainingInstitutionData(fromYear, toYear);
    }
}

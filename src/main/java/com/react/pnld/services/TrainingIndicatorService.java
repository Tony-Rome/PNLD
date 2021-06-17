package com.react.pnld.services;

import com.react.pnld.dto.TrainingIndicatorDTO;
import com.react.pnld.repo.TrainingIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingIndicatorService {

    @Autowired
    private TrainingIndicatorRepository trainingIndicatorRepository;

    public List<TrainingIndicatorDTO> trainingInstitutionData(int fromYear, int toYear) {
        return trainingIndicatorRepository.trainingInstitutionData(fromYear, toYear);
    }

    public List<TrainingIndicatorDTO> trainingTeacherData(int fromYear, int toYear){
        return trainingIndicatorRepository.trainingTeacherData(fromYear, toYear);
    }
}

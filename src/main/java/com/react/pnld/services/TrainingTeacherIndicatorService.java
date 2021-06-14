package com.react.pnld.services;

import com.react.pnld.dto.TrainingTeacherIndicatorDTO;
import com.react.pnld.repo.TrainingTeacherIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingTeacherIndicatorService {

    @Autowired
    private TrainingTeacherIndicatorRepository trainingTeacherIndicatorRepository;

    public List<TrainingTeacherIndicatorDTO> trainingTeacherData(int fromYear, int toYear){
        return trainingTeacherIndicatorRepository.trainingTeacherData(fromYear, toYear);
    }
}

package com.react.pnld.services;

import com.react.pnld.dto.indicator.TrainingIndicatorDTO;
import com.react.pnld.repo.TrainingIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingIndicatorService {

    @Autowired
    private TrainingIndicatorRepository trainingIndicatorRepository;

    public List<TrainingIndicatorDTO> trainingInstitutionData(int fromYear, int toYear) {
        return trainingIndicatorRepository.trainingInstitutionData(fromYear, toYear);
    }

    public List<TrainingIndicatorDTO> trainingTeacherData(int fromYear, int toYear){
        int FIRST_REGION_ID = 1;
        int LAST_REGION_ID = 17;

        List<TrainingIndicatorDTO> trainingTeacherIndicatorDTOList = new ArrayList<>();

        for(int i = FIRST_REGION_ID; i <= LAST_REGION_ID; i++){

            TrainingIndicatorDTO trainingTeacherIndicatorDTO = trainingIndicatorRepository.trainingTeacherData(fromYear, toYear, i);
            trainingTeacherIndicatorDTOList.add(trainingTeacherIndicatorDTO);
        }

        return trainingTeacherIndicatorDTOList;
    }
}

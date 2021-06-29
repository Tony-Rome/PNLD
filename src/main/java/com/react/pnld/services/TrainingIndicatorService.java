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
    private static final int FIRST_REGION_ID = 1;
    private static final int LAST_REGION_ID = 17;

    public List<TrainingIndicatorDTO> trainingInstitutionData(int fromYear, int toYear) {

        List<TrainingIndicatorDTO> trainingInstitutionIndicatorDTOList = new ArrayList<>();

        for(int i = FIRST_REGION_ID; i <= LAST_REGION_ID; i++){
            TrainingIndicatorDTO trainingInstitutionIndicatorDTO = trainingIndicatorRepository.trainingInstitutionData(fromYear, toYear, i);
            if(trainingInstitutionIndicatorDTO != null) trainingInstitutionIndicatorDTOList.add(trainingInstitutionIndicatorDTO);
        }

        return trainingInstitutionIndicatorDTOList;
    }

    public List<TrainingIndicatorDTO> trainingTeacherData(int fromYear, int toYear){

        List<TrainingIndicatorDTO> trainingTeacherIndicatorDTOList = new ArrayList<>();

        for(int i = FIRST_REGION_ID; i <= LAST_REGION_ID; i++){
            TrainingIndicatorDTO trainingTeacherIndicatorDTO = trainingIndicatorRepository.trainingTeacherData(fromYear, toYear, i);
            if(trainingTeacherIndicatorDTO != null) trainingTeacherIndicatorDTOList.add(trainingTeacherIndicatorDTO);
        }

        return trainingTeacherIndicatorDTOList;
    }
}

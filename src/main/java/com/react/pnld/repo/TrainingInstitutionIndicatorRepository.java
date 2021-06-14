package com.react.pnld.repo;

import com.react.pnld.dto.TrainingInstitutionIndicatorDTO;
import com.react.pnld.mappers.TrainingInstitutionIndicatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainingInstitutionIndicatorRepository {

    @Autowired
    private TrainingInstitutionIndicatorMapper trainingInstitutionIndicatorMapper;

    public List<TrainingInstitutionIndicatorDTO> trainingInstitutionData(int fromYear, int toYear) {
        return trainingInstitutionIndicatorMapper.trainingInstitutionData(fromYear, toYear);
    }
}

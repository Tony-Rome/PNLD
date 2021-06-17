package com.react.pnld.repo;

import com.react.pnld.dto.TrainingInstitutionIndicatorDTO;
import com.react.pnld.dto.TrainingTeacherIndicatorDTO;
import com.react.pnld.mappers.TrainingIndicatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainingIndicatorRepository {

    @Autowired
    private TrainingIndicatorMapper trainingIndicatorMapper;

    public List<TrainingInstitutionIndicatorDTO> trainingInstitutionData(int fromYear, int toYear) {
        return trainingIndicatorMapper.trainingInstitutionData(fromYear, toYear);
    }

    public List<TrainingTeacherIndicatorDTO> trainingTeacherData(int fromYear, int toYear) {
        return trainingIndicatorMapper.trainingTeacherData(fromYear, toYear);
    }
}

package com.react.pnld.repo;

import com.react.pnld.dto.indicator.TrainingIndicatorDTO;
import com.react.pnld.mappers.TrainingIndicatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainingIndicatorRepository {

    @Autowired
    private TrainingIndicatorMapper trainingIndicatorMapper;

    public TrainingIndicatorDTO trainingInstitutionData(int fromYear, int toYear, int regionId) {
        return trainingIndicatorMapper.trainingInstitutionData(fromYear, toYear, regionId);
    }

    public TrainingIndicatorDTO trainingTeacherData(int fromYear, int toYear, int regionId) {
        return trainingIndicatorMapper.trainingTeacherData(fromYear, toYear, regionId);
    }
}

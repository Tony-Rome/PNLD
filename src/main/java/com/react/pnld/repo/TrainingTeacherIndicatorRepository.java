package com.react.pnld.repo;

import com.react.pnld.dto.TrainingTeacherIndicatorDTO;
import com.react.pnld.mappers.TrainingTeacherIndicatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainingTeacherIndicatorRepository {

    @Autowired
    private TrainingTeacherIndicatorMapper trainingTeacherIndicatorMapper;

    public List<TrainingTeacherIndicatorDTO> trainingTeacherData(int fromYear, int toYear) {
        return trainingTeacherIndicatorMapper.trainingTeacherData(fromYear, toYear);
    }
}

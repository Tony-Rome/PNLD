package com.react.pnld.repo;

import com.react.pnld.dto.TrainingInstitutionIndicatorDTO;
import com.react.pnld.mappers.TrainingIndicatorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainingIndicatorRepository {

    private static final Logger logger = LoggerFactory.getLogger(TrainingIndicatorRepository.class);

    @Autowired
    private TrainingIndicatorMapper trainingIndicatorMapper;

    public List<TrainingInstitutionIndicatorDTO> participantInstitutionNumberPNLD(int fromYear, int toYear) {
        return trainingIndicatorMapper.participantInstitutionNumberPNLD(fromYear, toYear);
    }
}

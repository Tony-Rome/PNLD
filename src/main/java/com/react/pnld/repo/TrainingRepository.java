package com.react.pnld.repo;

import com.react.pnld.mappers.TrainingMapper;
import com.react.pnld.model.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingRepository {

    private static final Logger logger = LoggerFactory.getLogger(TrainingRepository.class);

    @Autowired
    private TrainingMapper trainingMapper;

    public Training getTrainingByFacilitator(String facilitatorName){
        return trainingMapper.getTrainingByFacilitator(facilitatorName);
    }
}

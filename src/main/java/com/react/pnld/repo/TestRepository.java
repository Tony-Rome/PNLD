package com.react.pnld.repo;

import com.react.pnld.mappers.TestMapper;
import com.react.pnld.model.Test;
import com.react.pnld.model.TrainingAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TestRepository {

    private static final Logger logger = LoggerFactory.getLogger(TestRepository.class);

    @Autowired
    private TestMapper testMapper;

    public Optional<Test> getTeacherTest(int teacherId, String testType){
        return Optional.ofNullable(testMapper.getTeacherTest(teacherId, testType));
    }

    public int insertTest(Test test){
        logger.info("insertTest. test={}", test);
        return testMapper.insertTest(test);
    }

    public int getNextTestId(){
        return testMapper.getNextTestId();
    }

    public int getNextTrainingAnswer(){
        return testMapper.getNextTrainingAnswer();
    }

    public int insertTrainingAnswer(TrainingAnswer trainingAnswer){
        logger.info("insertTrainingAnswer. trainingAnswer={}", trainingAnswer);
        return testMapper.insertTrainingAnswer(trainingAnswer);
    }
}

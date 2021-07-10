package com.react.pnld.repo;

import com.react.pnld.mappers.TestMapper;
import com.react.pnld.model.CTTest;
import com.react.pnld.model.TrainingTest;
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

    public Optional<TrainingTest> getTrainingTestByTeacherRut(String teacherRut, String testType) {
        return Optional.ofNullable(testMapper.getTrainingTestByTeacherRut(teacherRut, testType));
    }

    public int getNextTrainingTestId() {
        return testMapper.getNextTrainingTestId();
    }

    public int insertTrainingTest(TrainingTest trainingTest) {
        logger.info("insertTest. test={}", trainingTest);
        return testMapper.insertTrainingTest(trainingTest);
    }

    public Optional<CTTest> getTeacherCTTestByRut(String teacherRut){
        return Optional.ofNullable(testMapper.getTeacherCTTestByRut(teacherRut));
    }

    public int getNextCTTestId(){
        return testMapper.getNextCTTestId();
    }

    public int insertCTTest(CTTest newCTTest){
        return testMapper.insertCTTest(newCTTest);
    }
}

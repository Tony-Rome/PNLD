package com.react.pnld.mappers;

import com.react.pnld.model.TrainingTest;

public interface TestMapper {
    TrainingTest getTrainingTest(int teacherId, String testType);
    int getNextTrainingTestId();
    int insertTrainingTest(TrainingTest trainingTest);
}

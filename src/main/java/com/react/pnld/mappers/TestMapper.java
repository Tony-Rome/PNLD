package com.react.pnld.mappers;

import com.react.pnld.model.TrainingTest;

public interface TestMapper {
    TrainingTest getTrainingTestByTeacherRut(String teacherRut, String testType);

    int getNextTrainingTestId();

    int insertTrainingTest(TrainingTest trainingTest);
}

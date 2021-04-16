package com.react.pnld.mappers;

import com.react.pnld.model.Test;
import com.react.pnld.model.TrainingAnswer;

public interface TestMapper {

    Test getTeacherTest(int teacherId, String testType);

    int insertTest(Test test);

    int getNextTestId();

    int getNextTrainingAnswer();

    int insertTrainingAnswer(TrainingAnswer trainingAnswer);
}

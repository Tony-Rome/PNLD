package com.react.pnld.mappers;

import com.react.pnld.model.Test;
import com.react.pnld.model.TrainingAnswer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface TestMapper {

    Test getTeacherTest(int teacherId, String testType);

    int insertTest(Test test);

    int getNextTestId();

    int getNextTrainingAnswer();

    int insertTrainingAnswer(TrainingAnswer trainingAnswer);
}

package com.react.pnld.services;

import com.react.pnld.model.CTTest;
import com.react.pnld.model.TrainingTest;
import com.react.pnld.repo.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public int getNextTrainingTestId(){
        return testRepository.getNextTrainingTestId();
    }

    public Optional<TrainingTest> getTrainingTestByTeacherRut(String teacherRut, String testType){
        return testRepository.getTrainingTestByTeacherRut(teacherRut, testType);
    }

    public int saveTrainingTest(TrainingTest trainingTest){
        return testRepository.insertTrainingTest(trainingTest);
    }

    public Optional<CTTest> getTeacherCTTestByRut(String teacherRut){
        return testRepository.getTeacherCTTestByRut(teacherRut);
    }
}

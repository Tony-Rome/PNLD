package com.react.pnld.repo;

import com.react.pnld.mappers.TestMapper;
import com.react.pnld.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TestRepository {

    @Autowired
    private TestMapper testMapper;

    public Optional<Test> getTeacherTest(int teacherId, String typeTest){
        return Optional.ofNullable(testMapper.getTeacherTest(teacherId, typeTest));
    }

    public int insertTest(Test test){
        return testMapper.insertTest(test);
    }

}

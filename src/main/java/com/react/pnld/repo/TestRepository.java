package com.react.pnld.repo;

import com.react.pnld.mappers.TeacherMapper;
import com.react.pnld.mappers.TestMapper;
import com.react.pnld.model.Test;
import com.react.pnld.model.TrainingAnswer;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TestRepository {

    private static final Logger logger = LoggerFactory.getLogger(TestRepository.class);

    @Autowired
    private TestMapper testMapper;

  /*  @Autowired
    @Qualifier("sqlSessionFactoryPNLD")
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private TestMapper getTestMapper(){
        SqlSession session = this.sqlSessionFactory.openSession();
        TestMapper testMapper = session.getMapper(TestMapper.class);
        return testMapper;
    }*/

    public Optional<Test> getTeacherTest(int teacherId, String testType){
/*
        TestMapper testMapper = this.getTestMapper();
*/
        return Optional.ofNullable(testMapper.getTeacherTest(teacherId, testType));
    }

    public int insertTest(Test test){
        /*TestMapper testMapper = this.getTestMapper();*/
        logger.info("insertTest. test={}", test);
        return testMapper.insertTest(test);
    }

    public int getNextTestId(){

/*
        TestMapper testMapper = this.getTestMapper();
*/
        return testMapper.getNextTestId();
    }

    public int getNextTrainingAnswer(){
/*
        TestMapper testMapper = this.getTestMapper();
*/
        return testMapper.getNextTrainingAnswer();
    }

    public int insertTrainingAnswer(TrainingAnswer trainingAnswer){
/*
        TestMapper testMapper = this.getTestMapper();
*/
        logger.info("insertTrainingAnswer. trainingAnswer={}", trainingAnswer);
        return testMapper.insertTrainingAnswer(trainingAnswer);
    }
}

package com.react.pnld.repo;

import com.react.pnld.mappers.TeacherMapper;
import com.react.pnld.model.Teacher;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TeacherRepository {

    private static final Logger logger = LoggerFactory.getLogger(TeacherRepository.class);

    @Autowired
    private TeacherMapper teacherMapper;
   /* @Autowired
    @Qualifier("sqlSessionFactoryPNLD")
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private TeacherMapper getTeacherMapper(){
        SqlSession session = this.sqlSessionFactory.openSession();
        TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
        return teacherMapper;
    }*/

    public Optional<Teacher> getTeacher(String rut, String email){

       /* TeacherMapper teacherMapper = getTeacherMapper();*/
        return Optional.ofNullable(teacherMapper.getTeacher(rut, email));
    }

    public int insertTeacher(Teacher teacher){
/*
        TeacherMapper teacherMapper = getTeacherMapper();*/
        logger.info("insertTeacher. teacher={}",teacher);
        return teacherMapper.insertTeacher(teacher);
    }

    public int getNextTeacherId(){

   /*     TeacherMapper teacherMapper = getTeacherMapper();*/
        return teacherMapper.getNextTeacherId();
    }
}

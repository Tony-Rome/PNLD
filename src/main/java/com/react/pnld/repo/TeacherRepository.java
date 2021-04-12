package com.react.pnld.repo;

import com.react.pnld.mappers.TeacherMapper;
import com.react.pnld.model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TeacherRepository {

    private static final Logger logger = LoggerFactory.getLogger(TeacherRepository.class);

    @Autowired
    TeacherMapper teacherMapper;

    public Optional<Teacher> getTeacher(String rut, String email){
        return Optional.ofNullable(this.teacherMapper.getTeacher(rut, email));
    }

    public int insertTeacher(Teacher teacher){
        logger.info("insertTeacher. teacher={}",teacher);
        return this.teacherMapper.insertTeacher(teacher);
    }

    public int getNextTeacherId(){
        return this.teacherMapper.getNextTeacherId();
    }
}

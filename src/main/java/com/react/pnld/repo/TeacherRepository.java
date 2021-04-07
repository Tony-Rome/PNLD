package com.react.pnld.repo;

import com.react.pnld.mappers.TeacherMapper;
import com.react.pnld.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TeacherRepository {

    @Autowired
    TeacherMapper teacherMapper;

    public Optional<Teacher> getPerson(String rut, String email){
        return Optional.ofNullable(this.teacherMapper.getTeacher(rut, email));
    }

    public int insertTeacher(Teacher teacher){
        return this.teacherMapper.insertTeacher(teacher);
    }

    public int getNextTeacherId(){
        return this.teacherMapper.getNextTeacherId();
    }
}

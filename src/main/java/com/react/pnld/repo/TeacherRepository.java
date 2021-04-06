package com.react.pnld.repo;

import com.react.pnld.mappers.PersonMapper;
import com.react.pnld.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherRepository {

    @Autowired
    PersonMapper personMapper;

    public Teacher getTeacher(String email){
        return this.personMapper.getTeacher(email);
    }
}

package com.react.pnld.mappers;

import com.react.pnld.model.Teacher;

public interface TeacherMapper {


    Teacher getTeacher(String rut, String email);

    int insertTeacher(Teacher teacher);

    int getNextTeacherId();
}

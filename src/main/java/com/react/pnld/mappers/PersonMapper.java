package com.react.pnld.mappers;

import com.react.pnld.model.Teacher;

public interface PersonMapper {

    Teacher getTeacherByRut(String rut);

    int insertTeacher(Teacher teacher);

    int updateTeacher(Teacher teacher);
}

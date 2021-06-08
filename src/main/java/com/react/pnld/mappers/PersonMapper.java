package com.react.pnld.mappers;

import com.react.pnld.model.Teacher;

public interface PersonMapper {

    Teacher getTeacherPerson(String rut);

    int insertTeacher(Teacher teacher);

    int getNextTeacherId();

    int getNextPersonId();

    int updateTeacher(Teacher teacher);
}

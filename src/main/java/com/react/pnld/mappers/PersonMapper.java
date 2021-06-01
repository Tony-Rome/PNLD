package com.react.pnld.mappers;

import com.react.pnld.model.Person;
import com.react.pnld.model.Teacher;

public interface PersonMapper {

    Teacher getTeacherPerson(String rut);

    int insertTeacher(Teacher teacher);

    int insertPerson(Person person);

    int getNextTeacherId();

    int getNextPersonId();

    boolean checkIfEmailExists(String email);

    int updateTeacher(Teacher teacher);
}

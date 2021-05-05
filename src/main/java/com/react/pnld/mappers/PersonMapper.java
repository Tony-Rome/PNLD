package com.react.pnld.mappers;

import com.react.pnld.model.Person;
import com.react.pnld.model.School;
import com.react.pnld.model.Teacher;
import com.react.pnld.model.Training;

public interface PersonMapper {

    Teacher getTeacherPerson(String rut);

    int insertTeacher(Teacher teacher);

    int insertPerson(Person person);

    int getNextTeacherId();

    int getNextPersonId();

    int getNextSchoolId();

    School getSchool(String name);

    int insertSchool(School school);

    Training getTrainingByFacilitator(String facilitatorName);
}

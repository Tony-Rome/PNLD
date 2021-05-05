package com.react.pnld.repo;

import com.react.pnld.mappers.PersonMapper;
import com.react.pnld.model.Person;
import com.react.pnld.model.School;
import com.react.pnld.model.Teacher;
import com.react.pnld.model.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonRepository {

    private static final Logger logger = LoggerFactory.getLogger(PersonRepository.class);

    @Autowired
    private PersonMapper personMapper;

    public Optional<Teacher> getTeacherPerson(String rut) {
        return Optional.ofNullable(personMapper.getTeacherPerson(rut));
    }

    public int insertTeacher(Teacher teacher) {
        logger.info("insertTeacher. teacher={}", teacher);
        return personMapper.insertTeacher(teacher);
    }

    public int insertPerson(Person person){
        return personMapper.insertPerson(person);
    }

    public int getNextTeacherId() {
        return personMapper.getNextTeacherId();
    }

    public int getNextPersonId(){
        return personMapper.getNextPersonId();
    }

    public int getNextSchoolId(){
        return personMapper.getNextSchoolId();
    }

    public Optional<School> getSchool(String name){
        return Optional.ofNullable(personMapper.getSchool(name));
    }

    public int insertSchool(School school){
        logger.info("insertSchool. school={}", school);
        return personMapper.insertSchool(school);
    }

    public Training getTrainingByFacilitator(String facilitatorName){
        return personMapper.getTrainingByFacilitator(facilitatorName);
    }

}

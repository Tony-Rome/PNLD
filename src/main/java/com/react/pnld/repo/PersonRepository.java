package com.react.pnld.repo;

import com.react.pnld.mappers.PersonMapper;
import com.react.pnld.model.Person;
import com.react.pnld.model.Teacher;
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
        int resultInsertTeacher = personMapper.insertTeacher(teacher);
        logger.info("insertTeacher. teacher={}, resultInsertTeacher={}", teacher, resultInsertTeacher);
        return resultInsertTeacher;
    }

    public int insertPerson(Person person) {
        int resultInsertPerson = personMapper.insertPerson(person);
        logger.info("insertPerson. person={}, resultInsertPerson={}", person, resultInsertPerson);
        return  resultInsertPerson;
    }

    public int getNextTeacherId() {
        return personMapper.getNextTeacherId();
    }

    public int getNextPersonId() {
        return personMapper.getNextPersonId();
    }

    public boolean checkIfEmailExists(String email) {
        return personMapper.checkIfEmailExists(email);
    }

    public int updateTeacher(Teacher teacher){
        return personMapper.updateTeacher(teacher);
    }
}

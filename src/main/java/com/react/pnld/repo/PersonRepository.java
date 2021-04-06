package com.react.pnld.repo;

import com.react.pnld.mappers.PersonMapper;
import com.react.pnld.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonRepository {

    @Autowired
    PersonMapper personMapper;

    public Optional<Person> getPerson(String rut, String email){
        return Optional.ofNullable(this.personMapper.getPerson(rut, email));
    }

    public int insertPerson(Person person){
        return this.personMapper.insertPerson(person);
    }

    public int insertTeacherByPersonRut(String personRut){
        return this.personMapper.insertTeacherByPersonRut(personRut);
    }
}

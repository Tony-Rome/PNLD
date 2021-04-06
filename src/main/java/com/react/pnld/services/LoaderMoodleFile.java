package com.react.pnld.services;

import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.dto.PostTrainingDTO;
import com.react.pnld.model.Person;
import com.react.pnld.repo.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaderMoodleFile{

    private static final Logger logger = LoggerFactory.getLogger(LoaderMoodleFile.class);
    private static final int DUMMY_PERSON_ID = 0;
    private  static final String DELIMITER_LAST_NAMES = " ";

    @Autowired
    PersonRepository personRepository;

    public FileResumeDTO processPostTrainingRows(List<PostTrainingDTO> postTrainingRows) {

        logger.info("processTrainingRows. postTrainingRows.size()={}", postTrainingRows.size());

        for(PostTrainingDTO postTrainingRow : postTrainingRows){

            //TODO check docente exist, if dont then insert persona, gender, docente
            Optional<Person> personInModel = personRepository.getPerson(postTrainingRow.getRut(), postTrainingRow.getEmail());

            if(!personInModel.isPresent()){
                String[] lastNames = postTrainingRow.getLastNames().split(DELIMITER_LAST_NAMES);//TODO validate when only one lastname
                int idGenderNotSpecified = 4; //TODO get gender by type

                Person person = new Person(DUMMY_PERSON_ID, postTrainingRow.getName(), lastNames[0],
                        lastNames[1],postTrainingRow.getRut(), postTrainingRow.getEmail(), idGenderNotSpecified);

                int insertValue = this.insertPerson(person);

                if(insertValue > 0){
                    this.insertTeacherByPersonRut(person.getRut());
                }
            }
        }

        //TODO update entity test and pregunta

        return new FileResumeDTO();
    }

    public int insertPerson(Person person){
        return personRepository.insertPerson(person);
    }


    public int insertTeacherByPersonRut(String personRut){
        return personRepository.insertTeacherByPersonRut(personRut);
    }
}

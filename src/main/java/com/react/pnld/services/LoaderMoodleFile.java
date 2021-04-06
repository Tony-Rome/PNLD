package com.react.pnld.services;

import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.dto.PostTrainingDTO;
import com.react.pnld.model.Person;
import com.react.pnld.model.Test;
import com.react.pnld.model.TestQuestion;
import com.react.pnld.repo.PersonRepository;
import com.react.pnld.repo.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaderMoodleFile{

    private static final Logger logger = LoggerFactory.getLogger(LoaderMoodleFile.class);
    private static final int DUMMY_ID = 0;
    private  static final String DELIMITER_LAST_NAMES = " ";

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TestRepository testRepository;

    public FileResumeDTO processPostTrainingRows(List<PostTrainingDTO> postTrainingRows, int loadedFileId) {

        logger.info("processTrainingRows. postTrainingRows.size()={}", postTrainingRows.size());
        int newRecords = 0;
        int duplicatedRecords = 0;
        String POST_CAPACITA = "post-capacita";//TODO change for enum
        for(PostTrainingDTO postTrainingRow : postTrainingRows){

            //check docente exist, if dont then insert persona, gender, docente
            Optional<Person> personInModel = personRepository.getPerson(postTrainingRow.getRut(), postTrainingRow.getEmail());

            if(!personInModel.isPresent()){
                String[] lastNames = postTrainingRow.getLastNames().split(DELIMITER_LAST_NAMES);//TODO validate when only one lastname
                int idGenderNotSpecified = 4; //TODO get gender by type

                Person person = new Person(DUMMY_ID, postTrainingRow.getName(), lastNames[0],
                        lastNames[1],postTrainingRow.getRut(), postTrainingRow.getEmail(), idGenderNotSpecified);

                int insertValue = this.insertPerson(person);

                if(insertValue > 0){
                    this.insertTeacherByPersonRut(person.getRut());
                }

                personInModel = Optional.of(person);
            }

            Optional<Test> teacherTest = this.testRepository.getTeacherTest(personInModel.get().getId(), POST_CAPACITA);

            if(!teacherTest.isPresent()){
                Test test = new Test(DUMMY_ID, personInModel.get().getId(), loadedFileId, POST_CAPACITA,
                        postTrainingRow.getTestState(), postTrainingRow.getStartIn(), postTrainingRow.getFinishIn(),
                        postTrainingRow.getDuration(), postTrainingRow.getScore());

                this.testRepository.insertTest(test);
                int TEST_Insert_recent = 0;

                TestQuestion testQuestion = new TestQuestion(DUMMY_ID, TEST_Insert_recent, postTrainingRow.getAnswerOne(), postTrainingRow.getAnswerTwo(),
                        postTrainingRow.getAnswerThree(), postTrainingRow.getAnswerFour(), postTrainingRow.getAnswerFive(),
                        postTrainingRow.getAnswerSix(), postTrainingRow.getAnswerSeven(), postTrainingRow.getAnswerEight(),
                        postTrainingRow.getAnswerNine(), postTrainingRow.getAnswerTen());



                newRecords++;
            } else {
                duplicatedRecords++;
            }

        }

        return new FileResumeDTO(postTrainingRows.size(), newRecords, duplicatedRecords);
    }

    public int insertPerson(Person person){
        return personRepository.insertPerson(person);
    }


    public int insertTeacherByPersonRut(String personRut){
        return personRepository.insertTeacherByPersonRut(personRut);
    }
}

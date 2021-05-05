package com.react.pnld.services;

import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.dto.TrainingFileDTO;
import com.react.pnld.model.*;
import com.react.pnld.repo.PersonRepository;
import com.react.pnld.repo.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaderMoodleFile {

    private static final Logger logger = LoggerFactory.getLogger(LoaderMoodleFile.class);
    private static final String DELIMITER_LAST_NAMES = " ";
    private static final int GENDER_ID_NOT_SPECIFIED = 4;
    private static final int RBD_ID_NOT_SPECIFIED = 0;
    private static final String NOT_SPECIFIED = "no especificado";
    private static final int REGION_ID_OTHER = 17;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TestRepository testRepository;

    public FileResumeDTO processTrainingFileRows(List<TrainingFileDTO> postTrainingRows, int loadedFileId,
                                                 String testType) {

        logger.info("processTrainingFileRows. postTrainingRows.size()={}, loadedFileId={}, testType={}",
                postTrainingRows.size(), loadedFileId, testType);

        int newRecords = 0;
        int duplicatedRecords = 0;

        for (TrainingFileDTO postTrainingRow : postTrainingRows) {
            logger.info("processTrainingFileRows. postTrainingRow={}", postTrainingRow);

            //check docente exist, if dont then insert persona, gender, docente
            Optional<Teacher> teacherSelected = personRepository.getTeacherPerson(FileUtilService.
                    removeSymbols(postTrainingRow.getRut()));

            if (!teacherSelected.isPresent()) {
                Teacher teacher = this.buildTeacherFrom(postTrainingRow);
                this.personRepository.insertPerson(teacher);
                this.personRepository.insertTeacher(teacher);
                teacherSelected = Optional.of(teacher);
            }

            logger.info("processTrainingFileRows. teacherSelected.get()={}", teacherSelected.get());

            /*Optional<Test> teacherTest = testRepository.getTeacherTest(teacherSelected.get().getId(), testType);

            if (!teacherTest.isPresent()) {

                Test test = new Test(this.testRepository.getNextTestId(), teacherSelected.get().getId(), loadedFileId,
                        testType, postTrainingRow.getTestState(), postTrainingRow.getStartIn(),
                        postTrainingRow.getFinishIn(), postTrainingRow.getRequiredInterval(), postTrainingRow.getScore());

                int resultInsertTest = this.testRepository.insertTest(test);
                logger.info("processTrainingFileRows. resultInsertTest={}", resultInsertTest);

                TrainingAnswer trainingAnswer = new TrainingAnswer(this.testRepository.getNextTrainingAnswer(), test.getId(),
                        postTrainingRow.getAnswerOne(), postTrainingRow.getAnswerTwo(), postTrainingRow.getAnswerThree(),
                        postTrainingRow.getAnswerFour(), postTrainingRow.getAnswerFive(), postTrainingRow.getAnswerSix(),
                        postTrainingRow.getAnswerSeven(), postTrainingRow.getAnswerEight(), postTrainingRow.getAnswerNine(),
                        postTrainingRow.getAnswerTen());

                int resultInsertTrainingAnswer = this.testRepository.insertTrainingAnswer(trainingAnswer);
                logger.info("processTrainingFileRows. resultInsertTrainingAnswer={}", resultInsertTrainingAnswer);

                newRecords++;
            } else {
                duplicatedRecords++;
            }*/

        }

        return new FileResumeDTO(postTrainingRows.size(), newRecords, duplicatedRecords);
    }

    Teacher buildTeacherFrom(TrainingFileDTO postTrainingRow) {
        String[] lastNames = postTrainingRow.getLastNames().split(DELIMITER_LAST_NAMES);//TODO validate when only one lastname

        String institution = (postTrainingRow.getInstitution() == null || postTrainingRow.getInstitution().isEmpty())?
                NOT_SPECIFIED : postTrainingRow.getInstitution();
        Optional<School> school = personRepository.getSchool(institution.toLowerCase());

        if(!school.isPresent()){
            School newSchool = new School();
            newSchool.setId(personRepository.getNextSchoolId());
            newSchool.setName(institution.toLowerCase());
            newSchool.setCity(null);
            newSchool.setRegionId(REGION_ID_OTHER);
            newSchool.setRbd(RBD_ID_NOT_SPECIFIED);

            int resultInsertSchool = this.personRepository.insertSchool(newSchool);
            logger.info("buildTeacherFrom. resultInsertSchool={}", resultInsertSchool);
            school = Optional.of(newSchool);
        }

        String department = (postTrainingRow.getDepartment() == null || postTrainingRow.getDepartment().isEmpty())?
                NOT_SPECIFIED : postTrainingRow.getDepartment();

        Training training = this.personRepository.getTrainingByFacilitator(NOT_SPECIFIED);

        Teacher teacher = new Teacher();
        teacher.setTeacherId(this.personRepository.getNextTeacherId());
        teacher.setPersonId(this.personRepository.getNextPersonId());
        teacher.setRut(FileUtilService.removeSymbols(postTrainingRow.getRut().trim()));
        teacher.setAge(0);
        teacher.setDepartment(department);
        teacher.setParticipatedInPNLD(false);
        teacher.setTeachesInLevels(null);
        teacher.setTeachesSubjects(null);
        teacher.setCsResources(null);
        teacher.setRoboticsResources(null);
        teacher.setTrainingId(training.getId());
        //Person, super object
        teacher.setName(postTrainingRow.getName());
        teacher.setPaternalLastName(lastNames[0]);
        teacher.setMaternalLastName(lastNames[1]);
        teacher.setEmail(postTrainingRow.getEmail());
        teacher.setGenderId(GENDER_ID_NOT_SPECIFIED);
        teacher.setSchoolId(school.get().getId());
        return teacher;
    }

    public FileResumeDTO diagnosticoFile(LoadedFile loadedFile) {
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new FileResumeDTO();
    }

    public FileResumeDTO salidaFile(LoadedFile loadedFile) {
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new FileResumeDTO();
    }

    public FileResumeDTO satisFile(LoadedFile loadedFile) {
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new FileResumeDTO();
    }
}

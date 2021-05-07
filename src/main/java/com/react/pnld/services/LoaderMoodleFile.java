package com.react.pnld.services;

import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.dto.TrainingFileDTO;
import com.react.pnld.model.*;
import com.react.pnld.repo.PersonRepository;
import com.react.pnld.repo.SchoolRepository;
import com.react.pnld.repo.TestRepository;
import com.react.pnld.repo.TrainingRepository;
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

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    SchoolRepository schoolRepository;

    public FileResumeDTO processTrainingFileRows(List<TrainingFileDTO> postTrainingRows, int loadedFileId,
                                                 String testType) {

        logger.info("processTrainingFileRows. postTrainingRows.size()={}, loadedFileId={}, testType={}",
                postTrainingRows.size(), loadedFileId, testType);

        int newRecords = 0;
        int duplicatedRecords = 0;

        for (TrainingFileDTO postTrainingRow : postTrainingRows) {
            logger.info("processTrainingFileRows. postTrainingRow={}", postTrainingRow);

            Optional<Teacher> teacherSelected = personRepository.getTeacherPerson(FileUtilService.
                    removeSymbols(postTrainingRow.getRut()));

            if (!teacherSelected.isPresent()) {
                Teacher teacher = this.buildTeacherFrom(postTrainingRow);
                this.personRepository.insertPerson(teacher);
                this.personRepository.insertTeacher(teacher);
                teacherSelected = Optional.of(teacher);
            }

            logger.info("processTrainingFileRows. teacherSelected.get()={}", teacherSelected.get());

            Optional<TrainingTest> trainingTest = testRepository.getTrainingTest(teacherSelected.get().getTeacherId(),
                    testType);

            if (!trainingTest.isPresent()) {
                TrainingTest newTrainingTest = new TrainingTest();
                newTrainingTest.setId(this.testRepository.getNextTrainingTestId());
                newTrainingTest.setType(testType);
                newTrainingTest.setLoadedFileId(loadedFileId);
                newTrainingTest.setTeacherId(teacherSelected.get().getTeacherId());
                newTrainingTest.setInitDate(postTrainingRow.getStartIn());
                newTrainingTest.setEndDate(postTrainingRow.getFinishIn());
                newTrainingTest.setRequiredInterval(postTrainingRow.getRequiredInterval());
                newTrainingTest.setState(postTrainingRow.getTestState());
                newTrainingTest.setScore(postTrainingRow.getScore());
                newTrainingTest.setAnswers(null);//TODO set answers list

                int resultInsertTest = this.testRepository.insertTrainingTest(newTrainingTest);
                logger.info("processTrainingFileRows. resultInsertTest={}", resultInsertTest);
                newRecords++;
            } else {
                duplicatedRecords++;
            }

        }

        return new FileResumeDTO(postTrainingRows.size(), newRecords, duplicatedRecords);
    }

    Teacher buildTeacherFrom(TrainingFileDTO postTrainingRow) {

        Teacher teacher = new Teacher();
        teacher.setTeacherId(this.personRepository.getNextTeacherId());
        teacher.setPersonId(this.personRepository.getNextPersonId());
        teacher.setRut(FileUtilService.removeSymbols(postTrainingRow.getRut().trim()));
        teacher.setAge(0);
        teacher.setParticipatedInPNLD(false);
        teacher.setTeachesInLevels(null);
        teacher.setTeachesSubjects(null);
        teacher.setCsResources(null);
        teacher.setRoboticsResources(null);

        String department = (postTrainingRow.getDepartment() == null || postTrainingRow.getDepartment().isEmpty())?
                NOT_SPECIFIED : postTrainingRow.getDepartment();
        teacher.setDepartment(department);

        Training training = this.trainingRepository.getTrainingByFacilitator(NOT_SPECIFIED);
        teacher.setTrainingId(training.getId());

        //Person, super object
        String[] lastNames = postTrainingRow.getLastNames().split(DELIMITER_LAST_NAMES);//TODO validate when only one lastname
        teacher.setName(postTrainingRow.getName());
        teacher.setPaternalLastName(lastNames[0]);
        teacher.setMaternalLastName(lastNames[1]);
        teacher.setEmail(postTrainingRow.getEmail());
        teacher.setGenderId(GENDER_ID_NOT_SPECIFIED);

        String schoolName = postTrainingRow.getInstitution();
        teacher.setSchoolId(getTeacherSchoolByName(schoolName).getId());
        return teacher;
    }

    private School getTeacherSchoolByName(String schoolName){

        if (schoolName == null || schoolName.isEmpty()) {
            return schoolRepository.getSchoolByName(NOT_SPECIFIED).get();
        } else {
            Optional<School> schoolSelected = schoolRepository.getSchoolByName(schoolName);
            if(!schoolSelected.isPresent()){
                School newSchool = createNewSchool(schoolName, null, REGION_ID_OTHER, RBD_ID_NOT_SPECIFIED);
                schoolSelected = Optional.of(newSchool);
            }
            return schoolSelected.get();
        }
    }

    School createNewSchool(String name, String city, int regionId, int rbd){
        School newSchool = new School();
        newSchool.setId(schoolRepository.getNextSchoolId());
        newSchool.setName(name);
        newSchool.setCity(city);
        newSchool.setRegionId(regionId);
        newSchool.setRbd(rbd);

        int resultInsertSchool = this.schoolRepository.insertSchool(newSchool);
        logger.info("createNewSchool. resultInsertSchool={}", resultInsertSchool);
        return newSchool;
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

package com.react.pnld.services;

import com.react.pnld.dto.*;
import com.react.pnld.model.*;
import com.react.pnld.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class LoaderMoodleFile {

    private static final Logger logger = LoggerFactory.getLogger(LoaderMoodleFile.class);


    @Autowired
    PersonRepository personRepository;
    @Autowired
    TestRepository testRepository;
    @Autowired
    TrainingRepository trainingRepository;
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    GenderRepository genderRepository;
    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Autowired
    FileUtilService fileUtilService;

    public FileResumeDTO processTrainingFileRows(List<TrainingFileDTO> postTrainingRows, int loadedFileId,
                                                 String testType) {

        logger.info("processTrainingFileRows. postTrainingRows.size()={}, loadedFileId={}, testType={}",
                postTrainingRows.size(), loadedFileId, testType);

        int newRecords = 0;
        int duplicatedRecords = 0;

        for (TrainingFileDTO postTrainingRow : postTrainingRows) {
            logger.info("processTrainingFileRows. postTrainingRow={}", postTrainingRow);

            School school = getSchoolByName(FileUtilService.removeAccents(postTrainingRow.getSchoolName()));

            Optional<Teacher> teacherSelected = personRepository.getTeacherPerson(FileUtilService.
                    removeSymbols(postTrainingRow.getRut()));

            if (!teacherSelected.isPresent()) {
                Teacher teacher = this.buildTeacherFrom(postTrainingRow);
                teacher.setSchoolId(school.getId());
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

    Teacher buildTeacherFrom(TeacherPersonDTO teacherPersonDTO) { //TODO: Mover a otro archivo

        Teacher teacher = new Teacher();
        teacher.setTeacherId(this.personRepository.getNextTeacherId());
        teacher.setPersonId(this.personRepository.getNextPersonId());
        teacher.setRut(FileUtilService.removeSymbols(teacherPersonDTO.getRut().trim()));
        teacher.setAge(0);
        teacher.setParticipatedInPNLD(false);
        teacher.setTeachesInLevels(null);
        teacher.setTeachesSubjects(null);
        teacher.setCsResources(null);
        teacher.setRoboticsResources(null);

        String department = (teacherPersonDTO.getDepartment() == null || teacherPersonDTO.getDepartment().isEmpty())?
                FileUtilService.NOT_SPECIFIED : teacherPersonDTO.getDepartment();
        teacher.setDepartment(department);

        Training training = this.trainingRepository.getTrainingByFacilitator(FileUtilService.NOT_SPECIFIED);
        teacher.setTrainingId(training.getId());

        String[] lastNames = FileUtilService.splitLastNames(teacherPersonDTO.getLastNames());
        teacher.setName(teacherPersonDTO.getName());
        teacher.setPaternalLastName(lastNames[0]);
        teacher.setMaternalLastName(lastNames[1]);
        teacher.setEmail(teacherPersonDTO.getEmail());

        int genderId = genderRepository.getGenderIdByType(fileUtilService.genderStandardization(teacherPersonDTO.getGender()));
        teacher.setGenderId(genderId);

        return teacher;
    }

    private School getSchoolByName(String schoolName){ //TODO: Mover a otro archivo

        if (schoolName == null || schoolName.isEmpty()) {
            return schoolRepository.getSchoolByName(FileUtilService.NOT_SPECIFIED).get();
        }

        Optional<School> schoolSelected = schoolRepository.getSchoolByName(schoolName);

        if(!schoolSelected.isPresent()){
            School newSchool = createNewSchool(schoolName, null, FileUtilService.REGION_ID_OTHER, FileUtilService.RBD_ID_NOT_SPECIFIED);
            schoolSelected = Optional.of(newSchool);
        }
        return schoolSelected.get();

    }

    School createNewSchool(String name, String city, int regionId, int rbd){ //TODO: Mover a otro archivo
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

    void verifySchool(School school, String city, int regionId, int rbd){ //TODO: Mover a otro archivo

        if((school.getCity() == null || school.getCity().isEmpty()) && (city != null)){
            school.setCity(city);
        }
        if((school.getRegionId() == FileUtilService.REGION_ID_OTHER) && (regionId != FileUtilService.REGION_ID_OTHER)){
            school.setRegionId(regionId);
        }
        if((school.getRbd() == FileUtilService.RBD_ID_NOT_SPECIFIED) && (rbd != FileUtilService.RBD_ID_NOT_SPECIFIED)){
            school.setRbd(rbd);
        }

        //TODO Actualizar colegio
    }

    int getRegionId(String name){ //TODO: Mover a otro archivo
        if(name == null || name.isEmpty()){
            return FileUtilService.REGION_ID_OTHER;
        }
        String cleanedName = FileUtilService.cleanRegionName(name); //TODO: Agregar unit test
        return regionRepository.getRegionIdByName(cleanedName); //TODO Verificar cuando no existe nombre region
    }

    public FileResumeDTO diagnosticoFile(List<DiagnosticFileDTO> diagnosticRows, int loadedFileId) {

        int newRecords = 0;
        int duplicatedRecords = 0;

        for (DiagnosticFileDTO diagnosticRow : diagnosticRows) {


            int regionId = getRegionId(diagnosticRow.getRegion());

            School school = getSchoolByName(FileUtilService.removeAccents(diagnosticRow.getSchoolName()));

            if(school.getName() != FileUtilService.NOT_SPECIFIED){
                int rbd = fileUtilService.strToInt(diagnosticRow.getRbd());
                verifySchool(school, diagnosticRow.getCommune(), regionId, rbd);
            }

            String rut = FileUtilService.removeSymbolsFromRut(diagnosticRow.getRut());

            if(rut == null) continue; //TODO: Contar registros inválidos

            Optional<Teacher> teacherPersonSelected = personRepository.getTeacherPerson(rut);

            if (!teacherPersonSelected.isPresent()) {
                Teacher teacher = this.buildTeacherFrom(diagnosticRow);
                teacher.setSchoolId(school.getId());
                this.personRepository.insertPerson(teacher);
                this.personRepository.insertTeacher(teacher);
                teacherPersonSelected = Optional.of(teacher);
            }


            //TODO: verificar y actualizar atributos de teacherPerson
            //TODO: Person verificar: genderId & SchoolId

            logger.info("processTrainingFileRows. teacherSelected.get()={}", teacherPersonSelected.get());

            int diagnosticQuestionnaireCount = questionnaireRepository.getDiagnosticQuestionnaireCount(teacherPersonSelected.get().getTeacherId());

            if(diagnosticQuestionnaireCount < 1){

                String answersJson = "{\"clave\":\"valor\"}";

                int diagnosticQuestionnaireId = questionnaireRepository.getNextDiagnosticQuestionnaireId();

                DiagnosticQuestionnaire newDiagnosticQuestionnaire = new DiagnosticQuestionnaire(
                        diagnosticQuestionnaireId, loadedFileId, teacherPersonSelected.get().getTeacherId(),
                        diagnosticRow.getRespondentId(), diagnosticRow.getCollectorId(),
                        diagnosticRow.getCreatedDate(),diagnosticRow.getModifiedDate(), answersJson);


                questionnaireRepository.insertDiagnosticQuestionnaire(newDiagnosticQuestionnaire);
                newRecords++;
            }

            if(diagnosticQuestionnaireCount >= 1){
                duplicatedRecords++;
            }

        }

        return new FileResumeDTO(diagnosticRows.size(), newRecords, duplicatedRecords);
    }

    public FileResumeDTO exitSatisfactionFile(List<ExitSatisfactionFileDTO> exitSatisfactionRows, int loadedFileId) {

        int newRecords = 0;
        int duplicatedRecords = 0;

        for (ExitSatisfactionFileDTO exitSatisfactionRow : exitSatisfactionRows) {

            School school = getSchoolByName(FileUtilService.removeAccents(exitSatisfactionRow.getSchoolName()));

            String rut = FileUtilService.removeSymbolsFromRut(exitSatisfactionRow.getRut());

            if(rut == null) continue; //TODO: Contar registros inválidos

            Optional<Teacher> teacherPersonSelected = personRepository.getTeacherPerson(rut);

            if (!teacherPersonSelected.isPresent()) {
                Teacher teacher = this.buildTeacherFrom(exitSatisfactionRow);
                this.personRepository.insertPerson(teacher);
                this.personRepository.insertTeacher(teacher);
                teacherPersonSelected = Optional.of(teacher);
            }

            //TODO: Si existe TeacherPerson, se actualiza departamento y nombre


            int exitQuestionnaireCount = questionnaireRepository.getExitSatisfactionQuestionnaireCount(teacherPersonSelected.get().getTeacherId());

            if(exitQuestionnaireCount < 1){


                String answersJson = "{\"llave\":\"respuesta\"}";

                int exitSatisfactionQuestionnaireId = questionnaireRepository.getNextExitSatisfactionQuestionnaireId();

                ExitSatisfactionQuestionnaire newExitQuestionnaire = new ExitSatisfactionQuestionnaire(
                        exitSatisfactionQuestionnaireId, loadedFileId, teacherPersonSelected.get().getTeacherId(),
                        exitSatisfactionRow.getResponseId(),exitSatisfactionRow.getSendDate(), answersJson,
                        exitSatisfactionRow.getId(), exitSatisfactionRow.getCourse(), exitSatisfactionRow.getGroup());


                questionnaireRepository.insertExitSatisfactionQuestionnaire(newExitQuestionnaire);
                newRecords++;
            }

            if(exitQuestionnaireCount >= 1){
                duplicatedRecords++;
            }


        }
        return new FileResumeDTO(exitSatisfactionRows.size(), newRecords, duplicatedRecords);
    }

}

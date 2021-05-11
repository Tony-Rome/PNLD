package com.react.pnld.services;

import com.react.pnld.dto.DiagnosticFileDTO;
import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.dto.TrainingFileDTO;
import com.react.pnld.model.*;
import com.react.pnld.repo.*;
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
    @Autowired
    RegionRepository regionRepository;

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

    public FileResumeDTO diagnosticoFile(List<DiagnosticFileDTO> diagnosticRows, int loadedFileId, String loadedFileType) {

        int newRecords = 0;
        int duplicatedRecords = 0;

        String[] headers = fileUtilService.selectedHeadersArray(loadedFileType);

        for (DiagnosticFileDTO diagnosticRow : diagnosticRows) {

            int regionId = regionRepository.getRegionIdByName(
                    fileUtilService.normalizeString(diagnosticRow.getRegion()));

            String schoolName = fileUtilService.normalizeString(diagnosticRow.getSchoolName());
            Optional<School> optionalSchool = schoolRepository.getSchoolByName(schoolName);

            if(!optionalSchool.isPresent()){

                String cleanedRbd = diagnosticRow.getRbd().replaceAll("[^0-9]","");
                String rbd = cleanedRbd.length() > 6 ? null : cleanedRbd;

                int schoolId = schoolRepository.getNextSchoolId();

                School newSchool = new School(schoolId, schoolName, diagnosticRow.getCommune(), regionId, rbd);
                schoolRepository.insertSchool(newSchool);
                optionalSchool = Optional.of(newSchool);
            }

            if(optionalSchool.isPresent()){

                if(optionalSchool.get().getRbd() == "" || optionalSchool.get().getRbd() == null){

                    String cleanedRbd = diagnosticRow.getRbd().replaceAll("[^0-9]","");
                    String rbd = cleanedRbd.length() > 6 ? null : cleanedRbd;
                    optionalSchool.get().setRbd(rbd);
                }
                if(optionalSchool.get().getRegionId() <= 0 || optionalSchool.get().getRegionId() > 16){ //TODO: es null o es no especificado (?)
                    String regionNormalized = fileUtilService.normalizeString(diagnosticRow.getRegion());
                    int newRegionId = regionRepository.getRegionIdByName(regionNormalized);
                    optionalSchool.get().setRegionId(newRegionId);
                }
                if(optionalSchool.get().getCity() == null){
                    String newCity = fileUtilService.normalizeString(diagnosticRow.getCommune());
                    optionalSchool.get().setCity(newCity);
                }

                schoolRepository.updateSchool(optionalSchool.get());
            }


            String email = diagnosticRow.getEmail().toLowerCase();
            Optional<Person> optionalPerson = personRepository.getPersonByEmail(email);

            if(!optionalPerson.isPresent()){
                int personId = personRepository.getNextPersonId();
                String[] lastNames = fileUtilService.splitLastNames(diagnosticRow.getLastNames()); //TODO: Ver nombre compuesto con 2+ palabras

                String genderStandardized = fileUtilService.genderStandardization(diagnosticRow.getGender().toLowerCase());
                int genderId = genderRepository.getGenderIdByType(genderStandardized);

                Person newPerson = new Person(personId, diagnosticRow.getNames(), lastNames[0], lastNames[1], email, genderId, optionalSchool.get().getId());
                personRepository.insertPerson(newPerson);

                optionalPerson = Optional.of(newPerson);
            }

            if(optionalPerson.isPresent()){

                if( optionalPerson.get().getGenderId() < 1){
                    String genderStandardized = fileUtilService.genderStandardization(diagnosticRow.getGender().toLowerCase());
                    int genderId = genderRepository.getGenderIdByType(genderStandardized);
                    optionalPerson.get().setGenderId(genderId);
                }
                if( optionalPerson.get().getSchoolId() <= 0){
                    optionalPerson.get().setSchoolId(optionalSchool.get().getId());
                }

                personRepository.updatePerson(optionalPerson.get());

            }

            String cleanedRut = fileUtilService.removeSymbolsFromRut(diagnosticRow.getRut());
            Optional<Teacher> optionalTeacher = teacherRepository.getTeacherByRut(cleanedRut);

            if(!optionalTeacher.isPresent()){
                int teacherId = teacherRepository.getNextTeacherId();
                Teacher newTeacher = new Teacher(teacherId, optionalPerson.get().getId(), cleanedRut, diagnosticRow.getAge(), null, true, null);
                teacherRepository.insertTeacher(newTeacher);
                optionalTeacher = Optional.of(newTeacher);
            }

            int diagnosticQuestionnaireCount = questionnaireRepository.getDiagnosticQuestionnaireCount(optionalTeacher.get().getId());

            if(diagnosticQuestionnaireCount < 1){

                String answersJson = "{\"clave\":\"valor\"}";

                int diagnosticQuestionnaireId = questionnaireRepository.getNextDiagnosticQuestionnaireId();

                DiagnosticQuestionnaire newDiagnosticQuestionnaire = new DiagnosticQuestionnaire(
                        diagnosticQuestionnaireId, loadedFileId, optionalTeacher.get().getId(),
                        diagnosticRow.getRespondentId(), diagnosticRow.getCollectorId(),
                        Timestamp.valueOf(diagnosticRow.getCreatedDate()),
                        Timestamp.valueOf(diagnosticRow.getModifiedDate()), answersJson);


                questionnaireRepository.insertDiagnosticQuestionnaire(newDiagnosticQuestionnaire);
                newRecords++;
            }

            if(diagnosticQuestionnaireCount >= 1){
                duplicatedRecords++;
            }

        }

        return new FileResumeDTO(diagnosticRows.size(), newRecords, duplicatedRecords);
    }

    public FileResumeDTO exitSatisfactionFile(List<ExitFileDTO> exitFileDTORows, int loadedFileId, String loadedFileType) {

        int newRecords = 0;
        int duplicatedRecords = 0;

        for (ExitFileDTO exitFileDTORow : exitFileDTORows) {

            if(exitFileDTORow.getInstitution() != null && exitFileDTORow.getInstitution() != "") {

                String schoolName = fileUtilService.normalizeString(exitFileDTORow.getInstitution());

                Optional<School> optionalSchool = schoolRepository.getSchoolByName(schoolName);

                if (!optionalSchool.isPresent()) {
                    int schoolId = schoolRepository.getNextSchoolId();
                    int REGION_NOT_SPECIFIED = 16;

                    School newSchool = new School(schoolId, schoolName, null, REGION_NOT_SPECIFIED, null);
                    schoolRepository.insertSchool(newSchool);
                }
            }

            String cleanedRut = fileUtilService.removeSymbolsFromRut(exitFileDTORow.getRut());
            Optional<Teacher> optionalTeacher = teacherRepository.getTeacherByRut(cleanedRut);

            if(optionalTeacher.isPresent()){
                String department = exitFileDTORow.getDepartment();
                optionalTeacher.get().setDepartment(department);
                //TODO: teacherRepository.updateTeacher(optionalTeacher.get());
            }

            if(!optionalTeacher.isPresent()){
                //TODO: Verificar caso cuando no existe docente en cuestionario salida
                duplicatedRecords++;
                continue;
            }

            int exitQuestionnaireCount = questionnaireRepository.getExitQuestionnaireCount(optionalTeacher.get().getId());

            if(exitQuestionnaireCount < 1){


                String answersJson = "{\"llave\":\"respuesta\"}";

                int exitQuestionnaireId = questionnaireRepository.getNextExitQuestionnaireId();

                System.out.println("FECHA: " + exitFileDTORow.getSendDate());
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                String fecha = exitFileDTORow.getSendDate().replaceAll("/","-");
                System.out.println("FECHA REPLACE: " + fecha);
                try {
                    date = simpleDateFormat1.parse(fecha);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("NUEVA FECHA: " + simpleDateFormat2.format(date));

                ExitQuestionnaire newExitQuestionnaire = new ExitQuestionnaire(
                        exitQuestionnaireId, loadedFileId, optionalTeacher.get().getId(),
                        exitFileDTORow.getResponseId(), new Timestamp(date.getTime()), answersJson,
                        exitFileDTORow.getId(), exitFileDTORow.getCourse(), exitFileDTORow.getGroup());


                questionnaireRepository.insertExitQuestionnaire(newExitQuestionnaire);
                newRecords++;
            }

            if(exitQuestionnaireCount >= 1){
                duplicatedRecords++;
            }


        }
        return new FileResumeDTO(exitFileDTORows.size(), newRecords, duplicatedRecords);
    }

}

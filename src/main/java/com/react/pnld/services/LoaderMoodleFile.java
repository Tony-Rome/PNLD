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

        String schoolName = postTrainingRow.getInstitution(); //TODO: ESTO HAY QUE QUITARLO
        teacher.setSchoolId(getSchoolByName(schoolName).getId());
        return teacher;
    }

    private School getSchoolByName(String schoolName){ //TODO: Mover a otro archivo

        if (schoolName == null || schoolName.isEmpty()) {
            return schoolRepository.getSchoolByName(FileUtilService.NOT_SPECIFIED).get();
        } else {
            Optional<School> schoolSelected = schoolRepository.getSchoolByName(schoolName);
            if(!schoolSelected.isPresent()){
                School newSchool = createNewSchool(schoolName, null, FileUtilService.REGION_ID_OTHER, FileUtilService.RBD_ID_NOT_SPECIFIED);
                schoolSelected = Optional.of(newSchool);
            }
            return schoolSelected.get();
        }
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

        //TODO: UPDATE SCHOOL
    }

    int getRegionId(String name){ //TODO: Mover a otro archivo
        if(name == null || name.isEmpty()){
            return FileUtilService.REGION_ID_OTHER;
        }
        String cleanedName = FileUtilService.cleanRegionName(name); //TODO: Agregar unit test
        return regionRepository.getRegionIdByName(cleanedName); //TODO Verificar cuando no existe nombre region
    }

    int strToInt(String rbdStr){ //TODO: Mover a otro archivo
        String cleanedRbd = rbdStr.replaceAll("[^0-9]","");
        return Integer.parseInt(rbdStr);
    }

    public FileResumeDTO diagnosticoFile(List<DiagnosticFileDTO> diagnosticRows, int loadedFileId, String loadedFileType) {

        int newRecords = 0;
        int duplicatedRecords = 0;

        for (DiagnosticFileDTO diagnosticRow : diagnosticRows) {


            int regionId = getRegionId(diagnosticRow.getRegion());

            School school = getSchoolByName(FileUtilService.removeAccents(diagnosticRow.getSchoolName()));

            if(school.getName() != FileUtilService.NOT_SPECIFIED){ //TODO: Si no es epscificado igual s eregistra con los otros valores??
                int rbd = strToInt(diagnosticRow.getRbd());
                verifySchool(school, diagnosticRow.getCommune(), regionId, rbd);
            }

            String rut = FileUtilService.removeSymbolsFromRut(diagnosticRow.getRut());

            if(rut == null) continue;

            Optional<Teacher> teacherPearsonSelected = personRepository.getTeacherPerson(rut);

            if (!teacherPearsonSelected.isPresent()) {
                Teacher teacher = this.buildTeacherFrom(diagnosticRow);
                this.personRepository.insertPerson(teacher);
                this.personRepository.insertTeacher(teacher);
                teacherPearsonSelected = Optional.of(teacher);
            }


            //TODO: verificar y actualizar atributos de teacherPerson
            //TODO: Person verificar: genderId & SchoolId

            logger.info("processTrainingFileRows. teacherSelected.get()={}", teacherPearsonSelected.get());


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

    public FileResumeDTO exitSatisfactionFile(List<ExitSatisfactionFileDTO> exitSatisfactionRows, int loadedFileId, String loadedFileType) {

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

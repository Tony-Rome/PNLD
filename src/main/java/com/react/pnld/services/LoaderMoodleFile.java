package com.react.pnld.services;

import com.react.pnld.dto.*;
import com.react.pnld.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

@Service
public class LoaderMoodleFile {

    private static final Logger logger = LoggerFactory.getLogger(LoaderMoodleFile.class);

    @Autowired
    TestService testService;

    @Autowired
    PersonService personService;

    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    SchoolService schoolService;

    public FileResumeDTO processTrainingFileRows(List<TrainingFileDTO> trainingRows, int loadedFileId, String testType) {

        logger.info("processTrainingFileRows. postTrainingRows.size()={}, loadedFileId={}, testType={}",
                trainingRows.size(), loadedFileId, testType);

        int newRecordCount = 0;
        int duplicatedRecordCount = 0;
        int invalidRecordCount = 0;

        for (TrainingFileDTO trainingRowDTO : trainingRows) {
            logger.info("processTrainingFileRows. trainingRow={}", trainingRowDTO);

            String teacherRut = personService.clearRut(trainingRowDTO.getRut());

            if(!personService.rutValidator(teacherRut)){
                invalidRecordCount++;
            } else {
                Optional<Teacher> teacherSelected = personService.getTeacherByRut(teacherRut);

                if (!teacherSelected.isPresent()) {

                    String fullName = trainingRowDTO.getName() + " " + trainingRowDTO.getLastNames();

                    Optional<School> schoolSelected = schoolService.getSchoolByName(trainingRowDTO.getSchoolName());

                    Teacher newTeacher = new Teacher(teacherRut, fullName, 0, GenderProperties.GENDER_ID_NOT_SPECIFIED,
                            trainingRowDTO.getEmail(), trainingRowDTO.getDepartment(), false, null,
                            false, 0, schoolSelected.get().getRbd());
                    try {
                        int createTeacherResponse = personService.createTeacher(newTeacher);

                        logger.info("processTrainingFileRows. createTeacherResponse={}, newTeacher={}", createTeacherResponse, newTeacher);
                        Optional<TrainingTest> trainingTest = testService.getTrainingTestByTeacherRut(newTeacher.getRut(),
                                testType);

                        if (!trainingTest.isPresent()) {
                            int testId = testService.getNextTrainingTestId();
                            String answersJson = "{\"llave\":\"respuesta\"}"; //TODO replace to jsonb

                            TrainingTest newTrainingTest = new TrainingTest(testId, testType,
                                    loadedFileId, newTeacher.getRut(), trainingRowDTO.getStartIn(), trainingRowDTO.getFinishIn(),
                                    trainingRowDTO.getRequiredInterval(), trainingRowDTO.getTestState(), trainingRowDTO.getScore(),
                                    answersJson);

                            int resultInsertTest = testService.saveTrainingTest(newTrainingTest);
                            logger.info("processTrainingFileRows. resultInsertTest={}", resultInsertTest);
                            newRecordCount++;
                        } else {
                            duplicatedRecordCount++;
                        }
                    } catch (Exception e) {
                        logger.error("processTrainingFileRows. e.getMessage()={}", e.getMessage(), e);
                    }
                }
            }
        }
        return new FileResumeDTO(trainingRows.size(), newRecordCount, duplicatedRecordCount, invalidRecordCount);
    }

    public FileResumeDTO processDiagnosticFileRows(List<DiagnosticFileDTO> diagnosticRows, int loadedFileId) {

        int newRecordCount = 0;
        int duplicatedRecordCount = 0;
        int invalidRecordCount = 0;

        for (DiagnosticFileDTO diagnosticRow : diagnosticRows) {

            int schoolRbd = schoolService.rbdToInt(diagnosticRow.getRbd());
            String schoolName = this.removeAccents(diagnosticRow.getSchoolName());

            School school = schoolService.save(new School(schoolRbd, schoolName, diagnosticRow.getCommune(), null,
                    schoolService.getRegionId(diagnosticRow.getRegion())));

            String teacherRut = personService.clearRut(diagnosticRow.getRut());

            if(!personService.rutValidator(teacherRut)){
                invalidRecordCount++;
            } else {
                Optional<Teacher> teacherSelected = personService.getTeacherByRut(teacherRut);

                if (!teacherSelected.isPresent()) {
                    int teacherGender = personService.getGenderIdByType(personService.genderStandardization(diagnosticRow.getGender()));
                    Teacher newTeacher = new Teacher(teacherRut, diagnosticRow.getName(), diagnosticRow.getAge(),
                            teacherGender, diagnosticRow.getEmail(), null, false, null, false, 0,
                            school.getRbd());

                    try {
                        int createTeacherResponse = personService.createTeacher(newTeacher);
                        logger.info("processDiagnosticFileRows. createTeacherResponse={}, newTeacher={}", createTeacherResponse,
                                newTeacher);
                    } catch (Exception e) {
                        logger.error("processDiagnosticFileRows. e.getMessage={}", e.getMessage(), e);
                        continue;
                    }
                }

                Optional<DiagnosticQuestionnaire> diagnosticQuestionnaire = questionnaireService.getDiagnosticQuestByRut(teacherRut);

                if (!diagnosticQuestionnaire.isPresent()) {

                    String answersJson = "{\"clave\":\"valor\"}";

                    int diagnosticQuestionnaireId = questionnaireService.getNextDiagnosticQuestionnaireId();

                    DiagnosticQuestionnaire newDiagnosticQuestionnaire = new DiagnosticQuestionnaire(diagnosticQuestionnaireId,
                            loadedFileId, teacherRut, diagnosticRow.getRespondentId(), diagnosticRow.getCollectorId(),
                            diagnosticRow.getCreatedDate(), diagnosticRow.getModifiedDate(), answersJson);

                    int createDiagnosticResponse = questionnaireService.insertDiagnosticQuestionnaire(newDiagnosticQuestionnaire);
                    logger.info("processDiagnosticFileRows. createDiagnosticResponse={}", createDiagnosticResponse);

                    newRecordCount++;
                } else {
                    duplicatedRecordCount++;
                }
            }

        }
        return new FileResumeDTO(diagnosticRows.size(), newRecordCount, duplicatedRecordCount, invalidRecordCount);
    }

    public FileResumeDTO processSatisfactionFileRows(List<SatisfactionFileDTO> satisfactionRows, int loadedFileId) {

        int newRecordCount = 0;
        int duplicatedRecordCount = 0;
        int invalidRecordCount = 0;

        for (SatisfactionFileDTO satisfactionRowDTO : satisfactionRows) {
            logger.info("processSatisfactionFileRows. satisfactionRowDTO={}", satisfactionRowDTO);

            String teacherRut = personService.clearRut(satisfactionRowDTO.getRut());
            if(!personService.rutValidator(teacherRut)){
                invalidRecordCount++;
            } else {
                Optional<Teacher> teacherPerson = personService.getTeacherByRut(teacherRut);

                if(teacherPerson.isPresent()){

                    Optional<SatisfactionQuestionnaire> satisfactionQuestionnaire = questionnaireService.
                            getSatisfactionQuestByRut(teacherPerson.get().getRut());

                    if(satisfactionQuestionnaire.isPresent()){
                        duplicatedRecordCount++;
                    } else {
                        String answersJson = "{\"llave\":\"respuesta\"}"; //TODO replace to jsonb
                        int questionnaireId = questionnaireService.getNextSatisfactionQuestionnaireId();
                        int createQuestionnaireResponse = questionnaireService.saveSatisfactionQuestionnaire(
                                new SatisfactionQuestionnaire(questionnaireId, loadedFileId, teacherPerson.get().getRut(), satisfactionRowDTO.getResponseId(),
                                        satisfactionRowDTO.getSendDate(), answersJson, satisfactionRowDTO.getId(), satisfactionRowDTO.getCourse(),
                                        satisfactionRowDTO.getGroup()));

                        logger.info("processSatisfactionFileRows. createQuestionnaireResponse={}", createQuestionnaireResponse);
                        newRecordCount++;
                    }
                }  else {
                    invalidRecordCount++;
                }
            }
        }

        return new FileResumeDTO(satisfactionRows.size(), newRecordCount, duplicatedRecordCount, invalidRecordCount);
    }

    public FileResumeDTO processGeneralResumeRows(List<GeneralResumeTrainingDTO> generalResumeRows, int loadedFileId){
        logger.info("processGeneralResumeRows. generalResumeRows.size()={}, loadedFileId={}",
                generalResumeRows.size(), loadedFileId);

        int newRecordCount = 0;
        int duplicatedRecordCount = 0;
        int invalidRecordCount = 0;

        for(GeneralResumeTrainingDTO generalResumeRow : generalResumeRows){
            logger.info("processGeneralResumeRows. generalResumeRow={}", generalResumeRow);

            School school = schoolService.save(new School(generalResumeRow.getRbd(), null, null, null,
                    generalResumeRow.getRegionId()));

            logger.info("processGeneralResumeRows. school={}", school);

            String rutCleaned = personService.clearRut(generalResumeRow.getRut().toLowerCase());
            Optional<Teacher> teacher = personService.getTeacherByRut(rutCleaned);

            if (teacher.isPresent()) {
                teacher.get().setTrainingApproved(generalResumeRow.isApproved());
                teacher.get().setTrainingYear(generalResumeRow.getTrainingYear());
                personService.updateTeacher(teacher.get());
                newRecordCount++;
            } //create teacher is not possible because data necessary are in other files
        }

        return new FileResumeDTO(generalResumeRows.size(), newRecordCount, duplicatedRecordCount, invalidRecordCount);
    }

    public String removeAccents(String toClean) {
        if (toClean == null) return new String();
        return Normalizer.normalize(toClean, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}

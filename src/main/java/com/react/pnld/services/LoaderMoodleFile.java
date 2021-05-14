package com.react.pnld.services;

import com.react.pnld.dto.DiagnosticFileDTO;
import com.react.pnld.dto.ExitSatisfactionFileDTO;
import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.dto.TrainingFileDTO;
import com.react.pnld.model.*;
import com.react.pnld.repo.QuestionnaireRepository;
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

    @Autowired
    TestRepository testRepository;
    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Autowired
    EntityUtilService entityUtilService;

    public FileResumeDTO processTrainingFileRows(List<TrainingFileDTO> postTrainingRows, int loadedFileId,
                                                 String testType) {

        logger.info("processTrainingFileRows. postTrainingRows.size()={}, loadedFileId={}, testType={}",
                postTrainingRows.size(), loadedFileId, testType);

        int newRecords = 0;
        int duplicatedRecords = 0;

        for (TrainingFileDTO postTrainingRow : postTrainingRows) {
            logger.info("processTrainingFileRows. postTrainingRow={}", postTrainingRow);

            School school = entityUtilService.getSchoolByName(postTrainingRow.getSchoolName());

            boolean rut = entityUtilService.validateTeacherByRut(postTrainingRow.getRut());
            boolean email = entityUtilService.validatePersonByEmail(postTrainingRow.getEmail());

            if (rut && email) {

                Optional<Teacher> teacherSelected = entityUtilService.getTeacherPersonByRut(postTrainingRow.getRut());

                if (!teacherSelected.isPresent()) {
                    Teacher teacher = entityUtilService.buildTeacherFrom(postTrainingRow, school.getId());
                    entityUtilService.createPerson(teacher);
                    entityUtilService.createTeacher(teacher);

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
        }
        return new FileResumeDTO(postTrainingRows.size(), newRecords, duplicatedRecords);
    }

    public FileResumeDTO diagnosticFile(List<DiagnosticFileDTO> diagnosticRows, int loadedFileId) {

        int newRecords = 0;
        int duplicatedRecords = 0;

        for (DiagnosticFileDTO diagnosticRow : diagnosticRows) {

            int regionId = entityUtilService.getRegionId(diagnosticRow.getRegion());

            School school = entityUtilService.getSchoolByName(diagnosticRow.getSchoolName());

            if (!school.getName().equals(entityUtilService.NOT_SPECIFIED))
                entityUtilService.verifySchool(school, diagnosticRow.getCommune(), regionId, diagnosticRow.getRbd());

            boolean rut = entityUtilService.validateTeacherByRut(diagnosticRow.getRut());
            boolean email = entityUtilService.validatePersonByEmail(diagnosticRow.getEmail());

            if (rut && email) {

                Optional<Teacher> teacherPersonSelected = entityUtilService.getTeacherPersonByRut(diagnosticRow.getRut());

                if (!teacherPersonSelected.isPresent()) {
                    Teacher teacher = entityUtilService.buildTeacherFromDiagnostic(diagnosticRow, school.getId());
                    entityUtilService.createPerson(teacher);
                    entityUtilService.createTeacher(teacher);
                    teacherPersonSelected = Optional.of(teacher);
                }

                if (teacherPersonSelected.isPresent())
                    entityUtilService.verifyTeacherPersonFromDiagnostic(teacherPersonSelected.get(), diagnosticRow);

                logger.info("processTrainingFileRows. teacherSelected.get()={}", teacherPersonSelected.get());

                int diagnosticQuestionnaireCount = questionnaireRepository.getDiagnosticQuestionnaireCount(teacherPersonSelected.get().getTeacherId());

                if (diagnosticQuestionnaireCount < 1) {

                    String answersJson = "{\"clave\":\"valor\"}";

                    int diagnosticQuestionnaireId = questionnaireRepository.getNextDiagnosticQuestionnaireId();

                    DiagnosticQuestionnaire newDiagnosticQuestionnaire = new DiagnosticQuestionnaire();
                    newDiagnosticQuestionnaire.setId(diagnosticQuestionnaireId);
                    newDiagnosticQuestionnaire.setLoadedFileId(loadedFileId);
                    newDiagnosticQuestionnaire.setTeacherId(teacherPersonSelected.get().getTeacherId());
                    newDiagnosticQuestionnaire.setRespondentId(diagnosticRow.getRespondentId());
                    newDiagnosticQuestionnaire.setCollectorId(diagnosticRow.getCollectorId());
                    newDiagnosticQuestionnaire.setCreatedDate(diagnosticRow.getCreatedDate());
                    newDiagnosticQuestionnaire.setModifiedDate(diagnosticRow.getModifiedDate());
                    newDiagnosticQuestionnaire.setAnswers(answersJson);

                    questionnaireRepository.insertDiagnosticQuestionnaire(newDiagnosticQuestionnaire);
                    newRecords++;
                }
                if (diagnosticQuestionnaireCount >= 1) {
                    duplicatedRecords++;
                }
            }


        }
        return new FileResumeDTO(diagnosticRows.size(), newRecords, duplicatedRecords);
    }

    public FileResumeDTO exitSatisfactionFile(List<ExitSatisfactionFileDTO> exitSatisfactionRows, int loadedFileId) {

        int newRecords = 0;
        int duplicatedRecords = 0;

        for (ExitSatisfactionFileDTO exitSatisfactionRow : exitSatisfactionRows) {

            School school = entityUtilService.getSchoolByName(exitSatisfactionRow.getSchoolName());

            boolean rut = entityUtilService.validateTeacherByRut(exitSatisfactionRow.getRut());

            if (rut) {

                Optional<Teacher> teacherPersonSelected = entityUtilService.getTeacherPersonByRut(exitSatisfactionRow.getRut());

                if (!teacherPersonSelected.isPresent()) {
                    Teacher teacher = entityUtilService.buildTeacherFromExitSatisfaction(exitSatisfactionRow, school.getId());
                    entityUtilService.createPerson(teacher);
                    entityUtilService.createTeacher(teacher);
                    teacherPersonSelected = Optional.of(teacher);
                }

                if (teacherPersonSelected.isPresent())
                    entityUtilService.verifyTeacherPersonFromExistSatisfaction(teacherPersonSelected.get(), exitSatisfactionRow);

                int exitSatisfactionQuestionnaireCount = questionnaireRepository.getExitSatisfactionQuestionnaireCount(teacherPersonSelected.get().getTeacherId());

                if (exitSatisfactionQuestionnaireCount < 1) {

                    String answersJson = "{\"llave\":\"respuesta\"}";

                    int exitSatisfactionQuestionnaireId = questionnaireRepository.getNextExitSatisfactionQuestionnaireId();

                    ExitSatisfactionQuestionnaire newExitQuestionnaire = new ExitSatisfactionQuestionnaire();
                    newExitQuestionnaire.setId(exitSatisfactionQuestionnaireId);
                    newExitQuestionnaire.setLoadedFileId(loadedFileId);
                    newExitQuestionnaire.setTeacherId(teacherPersonSelected.get().getTeacherId());
                    newExitQuestionnaire.setResponseId(exitSatisfactionRow.getResponseId());
                    newExitQuestionnaire.setSendDate(exitSatisfactionRow.getSendDate());
                    newExitQuestionnaire.setAnswers(answersJson);
                    newExitQuestionnaire.setNumberId(exitSatisfactionRow.getId());
                    newExitQuestionnaire.setCourse(exitSatisfactionRow.getCourse());
                    newExitQuestionnaire.setGroup(exitSatisfactionRow.getGroup());

                    questionnaireRepository.insertExitSatisfactionQuestionnaire(newExitQuestionnaire);
                    newRecords++;
                }
                if (exitSatisfactionQuestionnaireCount >= 1) {
                    duplicatedRecords++;
                }
            }
        }
        return new FileResumeDTO(exitSatisfactionRows.size(), newRecords, duplicatedRecords);
    }
}

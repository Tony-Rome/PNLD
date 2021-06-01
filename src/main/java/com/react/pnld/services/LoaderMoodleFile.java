package com.react.pnld.services;

import com.react.pnld.dto.*;
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

    public FileResumeDTO processTrainingFileRows(List<TrainingFileDTO> trainingRows, int loadedFileId,
                                                 String testType) {

        logger.info("processTrainingFileRows. postTrainingRows.size()={}, loadedFileId={}, testType={}",
                trainingRows.size(), loadedFileId, testType);

        int newRecords = 0;
        int duplicatedRecords = 0;

        for (TrainingFileDTO trainingRow : trainingRows) {
            logger.info("processTrainingFileRows. trainingRow={}", trainingRow);

            String rutCleaned = EntityAttributeUtilService.clearRut(trainingRow.getRut().toLowerCase());
            Optional<Teacher> teacherSelected = entityUtilService.getTeacherPersonByRut(rutCleaned);

            if (!teacherSelected.isPresent()) {
                String cleanSchoolName = EntityAttributeUtilService.removeAccents(trainingRow.getSchoolName());
                Optional<School> school = entityUtilService.getSchoolWhereName(cleanSchoolName);

                Teacher teacher = entityUtilService.buildTeacherFrom(trainingRow, school.get().getId());
                teacher.setRut(rutCleaned);
                entityUtilService.createPerson(teacher);
                entityUtilService.createTeacher(teacher);

                teacherSelected = Optional.of(teacher);
            }

            logger.info("processTrainingFileRows. teacherSelected.get()={}", teacherSelected.get());
            Optional<TrainingTest> trainingTest = testRepository.getTrainingTest(teacherSelected.get().getTeacherId(),
                    testType);

            if (!trainingTest.isPresent()) {
                TrainingTest newTrainingTest = new TrainingTest(this.testRepository.getNextTrainingTestId(), testType,
                        loadedFileId, teacherSelected.get().getTeacherId(), trainingRow.getStartIn(), trainingRow.getFinishIn(),
                        trainingRow.getRequiredInterval(), trainingRow.getTestState(), trainingRow.getScore(), null);

                int resultInsertTest = this.testRepository.insertTrainingTest(newTrainingTest);
                logger.info("processTrainingFileRows. resultInsertTest={}", resultInsertTest);
                newRecords++;
            } else {
                duplicatedRecords++;
            }

            boolean isRutValid = EntityAttributeUtilService.rutValidator(rutCleaned);
            boolean isEmailValid = entityUtilService.validatePersonByEmail(trainingRow.getEmail());
        }
        return new FileResumeDTO(trainingRows.size(), newRecords, duplicatedRecords);
    }

    public FileResumeDTO diagnosticFile(List<DiagnosticFileDTO> diagnosticRows, int loadedFileId) {

        int newRecords = 0;
        int duplicatedRecords = 0;

        for (DiagnosticFileDTO diagnosticRow : diagnosticRows) {

            Optional<School> school = entityUtilService.getSchoolWhereName(diagnosticRow.getSchoolName());

            if(!school.isPresent()){
                school = Optional.of(entityUtilService.createNewSchool(diagnosticRow.getSchoolName(), null,
                        diagnosticRow.getCommune(), diagnosticRow.getRegion(), diagnosticRow.getRbd()));
            } else {
                entityUtilService.updateSchool(school.get(), null,
                        EntityAttributeUtilService.rbdToInt(diagnosticRow.getRbd()).intValue(),
                        entityUtilService.getRegionId(diagnosticRow.getRegion()), null, diagnosticRow.getCommune());
            }

            boolean rut = EntityAttributeUtilService.rutValidator(diagnosticRow.getRut());
            boolean email = entityUtilService.validatePersonByEmail(diagnosticRow.getEmail());

            if (rut && email) {

                Optional<Teacher> teacherPersonSelected = entityUtilService.getTeacherPersonByRut(diagnosticRow.getRut());

                if (!teacherPersonSelected.isPresent()) {
                    Teacher teacher = entityUtilService.buildTeacherFromDiagnostic(diagnosticRow, school.get().getId());
                    entityUtilService.createPerson(teacher);
                    entityUtilService.createTeacher(teacher);
                    teacherPersonSelected = Optional.of(teacher);
                }

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

    public FileResumeDTO satisfactionFile(List<SatisfactionFileDTO> satisfactionRows, int loadedFileId) {

        int newRecords = 0;
        int duplicatedRecords = 0;

        for (SatisfactionFileDTO satisfactionRow : satisfactionRows) {

            Optional<School> school = entityUtilService.getSchoolWhereName(satisfactionRow.getSchoolName());

            if(!school.isPresent())
                school = Optional.of(entityUtilService.createNewSchool(satisfactionRow.getSchoolName(), null, null, null, null));

            boolean rut = EntityAttributeUtilService.rutValidator(satisfactionRow.getRut());

            if (rut) {

                Optional<Teacher> teacherPersonSelected = entityUtilService.getTeacherPersonByRut(satisfactionRow.getRut());

                if (!teacherPersonSelected.isPresent()) {
                    Teacher teacher = entityUtilService.buildTeacherFromExitSatisfaction(satisfactionRow, school.get().getId());
                    entityUtilService.createPerson(teacher);
                    entityUtilService.createTeacher(teacher);
                    teacherPersonSelected = Optional.of(teacher);
                }

                int satisfactionQuestionnaireCount = questionnaireRepository.getSatisfactionQuestionnaireCount(teacherPersonSelected.get().getTeacherId());

                if (satisfactionQuestionnaireCount < 1) {

                    String answersJson = "{\"llave\":\"respuesta\"}";

                    int satisfactionQuestionnaireId = questionnaireRepository.getNextSatisfactionQuestionnaireId();

                    SatisfactionQuestionnaire newSatisfactionQuestionnaire = new SatisfactionQuestionnaire();
                    newSatisfactionQuestionnaire.setId(satisfactionQuestionnaireId);
                    newSatisfactionQuestionnaire.setLoadedFileId(loadedFileId);
                    newSatisfactionQuestionnaire.setTeacherId(teacherPersonSelected.get().getTeacherId());
                    newSatisfactionQuestionnaire.setResponseId(satisfactionRow.getResponseId());
                    newSatisfactionQuestionnaire.setSendDate(satisfactionRow.getSendDate());
                    newSatisfactionQuestionnaire.setAnswers(answersJson);
                    newSatisfactionQuestionnaire.setNumberId(satisfactionRow.getId());
                    newSatisfactionQuestionnaire.setCourse(satisfactionRow.getCourse());
                    newSatisfactionQuestionnaire.setGroup(satisfactionRow.getGroup());

                    questionnaireRepository.insertSatisfactionQuestionnaire(newSatisfactionQuestionnaire);
                    newRecords++;
                }
                if (satisfactionQuestionnaireCount >= 1) {
                    duplicatedRecords++;
                }
            }
        }
        return new FileResumeDTO(satisfactionRows.size(), newRecords, duplicatedRecords);
    }

    public FileResumeDTO processGeneralResumeRows(List<GeneralResumeTrainingDTO> generalResumeRows, int loadedFileId){
        logger.info("processGeneralResumeRows. generalResumeRows.size()={}, loadedFileId={}",
                generalResumeRows.size(), loadedFileId);

        int newRecords = 0;
        int duplicatedRecords = 0;

        for(GeneralResumeTrainingDTO generalResumeRow : generalResumeRows){
            logger.info("processGeneralResumeRows. generalResumeRow={}", generalResumeRow);

            Optional<School> school = entityUtilService.getSchoolWhereRbd(generalResumeRow.getRbd());

            if(!school.isPresent()){
                School newSchool = entityUtilService.createSchool(null, null, null, generalResumeRow.getRegionId(), generalResumeRow.getRbd());
                school = Optional.of(newSchool);
            } else {
                int updateResponse = entityUtilService.updateSchool(school.get(), null, generalResumeRow.getRbd(), generalResumeRow.getRegionId(), null, null);
                logger.info("processGeneralResumeRows. updateResponse={}", updateResponse);
            }

            logger.info("processGeneralResumeRows. school={}", school.get());

            String rutCleaned = EntityAttributeUtilService.clearRut(generalResumeRow.getRut().toLowerCase());
            Optional<Teacher> teacher = entityUtilService.getTeacherPersonByRut(rutCleaned);

            if(teacher.isPresent()){
                entityUtilService.updateTeacher(teacher.get(), 0, null, true, null,
                        generalResumeRow.isApproved(), generalResumeRow.getTrainingYear());
                newRecords++;
            } //create teacher is not possible because data necessary are in other files
        }

        return new FileResumeDTO(generalResumeRows.size(), newRecords, duplicatedRecords);
    }
}

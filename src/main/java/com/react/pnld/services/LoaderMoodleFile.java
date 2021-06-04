package com.react.pnld.services;

import com.react.pnld.dto.*;
import com.react.pnld.model.*;
import com.react.pnld.repo.TestRepository;
import org.modelmapper.ModelMapper;
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
    EntityUtilService entityUtilService;//TODO eliminate

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

        for (TrainingFileDTO trainingRow : trainingRows) {
            logger.info("processTrainingFileRows. trainingRow={}", trainingRow);

            Optional<Teacher> teacherSelected = personService.getTeacherByRut(trainingRow.getRut());

            if (!teacherSelected.isPresent()) {
                String cleanSchoolName = EntityAttributeUtilService.removeAccents(trainingRow.getSchoolName());
                Optional<School> school = entityUtilService.getSchoolWhereName(cleanSchoolName);

                Teacher teacher = personService.buildTeacherFrom(trainingRow, school.get().getId());
                teacher.setRut(rutCleaned);
                personService.saveTeacher(teacher);
                personService.createTeacher(teacher);

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
                newRecordCount++;
            } else {
                duplicatedRecordCount++;
            }

            boolean isRutValid = EntityAttributeUtilService.rutValidator(rutCleaned);
            boolean isEmailValid = personService.validatePersonByEmail(trainingRow.getEmail());
        }
        return new FileResumeDTO(trainingRows.size(), newRecordCount, duplicatedRecordCount, invalidRecordCount);
    }

    public FileResumeDTO diagnosticFile(List<DiagnosticFileDTO> diagnosticRows, int loadedFileId) {

        int newRecordCount = 0;
        int duplicatedRecordCount = 0;
        int invalidRecordCount = 0;

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
            boolean email = personService.validatePersonByEmail(diagnosticRow.getEmail());

            if (rut && email) {

                Optional<Teacher> teacherPersonSelected = personService.getTeacherByRut(diagnosticRow.getRut());

                if (!teacherPersonSelected.isPresent()) {
                    Teacher teacher = personService.buildTeacherFromDiagnostic(diagnosticRow, school.get().getId());
                    personService.saveTeacher(teacher);
                    personService.createTeacher(teacher);
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
                    newRecordCount++;
                }
                if (diagnosticQuestionnaireCount >= 1) {
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

            Optional<School> school = entityUtilService.getSchoolWhereRbd(generalResumeRow.getRbd());

            if(!school.isPresent()){
                School newSchool = entityUtilService.createSchool(null, null, null, generalResumeRow.getRegionId(), generalResumeRow.getRbd());
                school = Optional.of(newSchool);
            } else {
                int updateResponse = entityUtilService.updateSchool(school.get(), null, generalResumeRow.getRbd(), generalResumeRow.getRegionId(), null, null);
                logger.info("processGeneralResumeRows. updateResponse={}", updateResponse);
            }

            logger.info("processGeneralResumeRows. school={}", school.get());

            String rutCleaned = personService.clearRut(generalResumeRow.getRut().toLowerCase());
            Optional<Teacher> teacher = personService.getTeacherByRut(rutCleaned);

            if(teacher.isPresent()){
                personService.updateTeacher(teacher.get(), 0, null, true, null,
                        generalResumeRow.isApproved(), generalResumeRow.getTrainingYear());
                newRecordCount++;
            } //create teacher is not possible because data necessary are in other files
        }

        return new FileResumeDTO(generalResumeRows.size(), newRecordCount, duplicatedRecordCount, invalidRecordCount);
    }

}

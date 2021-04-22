package com.react.pnld.services;

import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.dto.TrainingFileDTO;
import com.react.pnld.model.LoadedFile;
import com.react.pnld.model.Teacher;
import com.react.pnld.model.Test;
import com.react.pnld.model.TrainingAnswer;
import com.react.pnld.repo.TeacherRepository;
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
    private static final int DUMMY_ID = 0;
    private static final String DELIMITER_LAST_NAMES = " ";

    @Autowired
    TeacherRepository teacherRepository;

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
            Optional<Teacher> teacherSelected = teacherRepository.getTeacher(postTrainingRow.getRut(),
                    postTrainingRow.getEmail());

            if (!teacherSelected.isPresent()) {
                Teacher teacher = this.buildTeacherFrom(postTrainingRow);
                teacher.setId(this.teacherRepository.getNextTeacherId());
                this.teacherRepository.insertTeacher(teacher);
                teacherSelected = Optional.of(teacher);
            }

            logger.info("processTrainingFileRows. teacherSelected.get()={}", teacherSelected.get());

            Optional<Test> teacherTest = testRepository.getTeacherTest(teacherSelected.get().getId(), testType);

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
            }

        }

        return new FileResumeDTO(postTrainingRows.size(), newRecords, duplicatedRecords);
    }

    Teacher buildTeacherFrom(TrainingFileDTO postTrainingRow) {
        String[] lastNames = postTrainingRow.getLastNames().split(DELIMITER_LAST_NAMES);//TODO validate when only one lastname
        int idGenderNotSpecified = 4; //TODO get gender by type

        return new Teacher(DUMMY_ID, postTrainingRow.getName(), lastNames[0],
                lastNames[1], postTrainingRow.getRut(), postTrainingRow.getEmail(), idGenderNotSpecified);
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

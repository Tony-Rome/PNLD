package com.react.pnld.services;

import com.react.pnld.dto.CTRowGroupADTO;
import com.react.pnld.dto.CTRowStudentsDTO;
import com.react.pnld.dto.CTRowTeacherDTO;
import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.model.CTTest;
import com.react.pnld.model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaderCTFiles {

    private static final Logger logger = LoggerFactory.getLogger(LoaderCTFiles.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private TestService testService;

    public FileResumeDTO processStudentsGroupOneRows(List<CTRowGroupADTO> studentsGroupOneRows) {
        logger.info("testFirstGroupStudents. ctFirstGroupStudents.size()={}", studentsGroupOneRows.size());
        //TODO validate load records by file's type

        //TODO insert records if not exist
        return new FileResumeDTO();
    }

    public FileResumeDTO processStudentsGroupTwoRows(List<CTRowStudentsDTO> ctFirstGroupStudents) {
        logger.info("testFirstGroupStudents. ctFirstGroupStudents.size()={}", ctFirstGroupStudents.size());
        //TODO validate load records by file's type

        //TODO insert records if not exist
        return new FileResumeDTO();
    }

    public FileResumeDTO processTeacherRows(List<CTRowTeacherDTO> ctRowsTeacher, int idLoadedFile) {

        int newRecordsCount = 0;
        int duplicatedRecordsCount = 0;
        int invalidRecordsCount = 0;

        for (CTRowTeacherDTO ctRowTeacherDTO : ctRowsTeacher) {
            String teacherRut = personService.clearRut(ctRowTeacherDTO.getRut());
            if(!personService.rutValidator(teacherRut)){
                invalidRecordsCount++;
            } else {
                Optional<Teacher> teacherSelected = personService.getTeacherByRut(teacherRut);

                if (teacherSelected.isPresent()) {
                    Optional<CTTest> teacherCTTest = testService.getTeacherCTTestByRut(teacherRut);

                    if(teacherCTTest.isPresent()){
                        duplicatedRecordsCount++;
                    } else {

                        int newIdCTTest = testService.getNextCTTestId();
                        String answers = "{\"llave\":\"respuesta\"}";
                        CTTest newCTTest = new CTTest(newIdCTTest, idLoadedFile, ctRowTeacherDTO.getTimeStamp(), ctRowTeacherDTO.getScore(),
                                teacherRut, ctRowTeacherDTO.getYouKnowCode(), ctRowTeacherDTO.getYouKnowScratch(),
                                ctRowTeacherDTO.getInitTime(), ctRowTeacherDTO.getFinishTime(), ctRowTeacherDTO.getHowDidInTheTest(),
                                ctRowTeacherDTO.getHowInterestedInTech(), answers);

                        int resultInsertTest = testService.saveCTTest(newCTTest);
                        logger.info("processTeacherRows. resultInsertTest={}, newCTTest={}", resultInsertTest, newCTTest);

                        newRecordsCount++;
                    }

                } else {
                    logger.info("processTeacherRows. no exist teacherRut={}", teacherRut);
                    invalidRecordsCount++;
                }
            }

        }
        return new FileResumeDTO(ctRowsTeacher.size(), newRecordsCount, duplicatedRecordsCount, invalidRecordsCount);
    }

}

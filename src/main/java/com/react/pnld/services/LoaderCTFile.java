package com.react.pnld.services;

import com.react.pnld.dto.CTStudentsGroupOne;
import com.react.pnld.dto.CTTestStudentsDTO;
import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.model.LoadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaderCTFile {

    private static final Logger logger = LoggerFactory.getLogger(LoaderCTFile.class);

    public FileResumeDTO processStudentsGroupOneFileRows(List<CTStudentsGroupOne> studentsGroupOneRows) {
        logger.info("testFirstGroupStudents. ctFirstGroupStudents.size()={}", studentsGroupOneRows.size());
        //TODO validate load records by file's type

        //TODO insert records if not exist
        return new FileResumeDTO();
    }

    public FileResumeDTO processStudentsGroupTwoFileRows(List<CTTestStudentsDTO> ctFirstGroupStudents) {
        logger.info("testFirstGroupStudents. ctFirstGroupStudents.size()={}", ctFirstGroupStudents.size());
        //TODO validate load records by file's type

        //TODO insert records if not exist
        return new FileResumeDTO();
    }

    public FileResumeDTO processTeacherFileRows(LoadedFile loadedFile) {
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new FileResumeDTO();
    }

}

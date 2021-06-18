package com.react.pnld.services;

import com.react.pnld.dto.CTGroupOneRowDTO;
import com.react.pnld.dto.CTTestStudentsRowDTO;
import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.model.LoadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaderCTFiles {

    private static final Logger logger = LoggerFactory.getLogger(LoaderCTFiles.class);

    public FileResumeDTO processStudentsGroupOneRows(List<CTGroupOneRowDTO> studentsGroupOneRows) {
        logger.info("testFirstGroupStudents. ctFirstGroupStudents.size()={}", studentsGroupOneRows.size());
        //TODO validate load records by file's type

        //TODO insert records if not exist
        return new FileResumeDTO();
    }

    public FileResumeDTO processStudentsGroupTwoRows(List<CTTestStudentsRowDTO> ctFirstGroupStudents) {
        logger.info("testFirstGroupStudents. ctFirstGroupStudents.size()={}", ctFirstGroupStudents.size());
        //TODO validate load records by file's type

        //TODO insert records if not exist
        return new FileResumeDTO();
    }

    public FileResumeDTO processTeacherRows(LoadedFile loadedFile) {
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new FileResumeDTO();
    }

}

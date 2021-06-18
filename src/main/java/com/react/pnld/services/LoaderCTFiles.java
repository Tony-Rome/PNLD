package com.react.pnld.services;

import com.react.pnld.dto.CTRowGroupOneStudentsDTO;
import com.react.pnld.dto.CTRowStudentsDTO;
import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.model.LoadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaderCTFiles {

    private static final Logger logger = LoggerFactory.getLogger(LoaderCTFiles.class);

    public FileResumeDTO processStudentsGroupOneRows(List<CTRowGroupOneStudentsDTO> studentsGroupOneRows) {
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

    public FileResumeDTO processTeacherRows(LoadedFile loadedFile) {
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new FileResumeDTO();
    }

}

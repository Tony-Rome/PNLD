package com.react.pnld.services;

import com.react.pnld.dto.CTFileFirstGroupStudentsDTO;
import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.model.LoadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaderCTFile {

    private static final Logger logger = LoggerFactory.getLogger(LoaderCTFile.class);

    public FileResumeDTO testFirstGroupStudents(List<CTFileFirstGroupStudentsDTO> ctFirstGroupStudents) {
        logger.info("testFirstGroupStudents. ctFirstGroupStudents.size()={}", ctFirstGroupStudents.size());
        //TODO validate load records by file's type

        //TODO insert records if not exist
        return new FileResumeDTO();
    }

    public FileResumeDTO testPCTwoFile(LoadedFile loadedFile) {
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new FileResumeDTO();
    }

    public FileResumeDTO testPCThreeFile(LoadedFile loadedFile) {
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new FileResumeDTO();
    }

}

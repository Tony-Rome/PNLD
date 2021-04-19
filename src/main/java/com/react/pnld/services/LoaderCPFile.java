package com.react.pnld.services;

import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.model.LoadedFile;
import org.springframework.stereotype.Service;

@Service
public class LoaderCPFile {

    public FileResumeDTO testPCOneFile(LoadedFile loadedFile) {
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

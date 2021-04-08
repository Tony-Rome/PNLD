package com.react.pnld.services;

import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.model.LoadedFile;
import org.springframework.stereotype.Service;

@Service
public class LoaderCodeFile {

    public FileResumeDTO processTeacherRosterFile(LoadedFile loadedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new FileResumeDTO();
    }

    public FileResumeDTO processTeacherOptInFile(LoadedFile loadedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new FileResumeDTO();
    }

    public FileResumeDTO processStudentLevelFile(LoadedFile loadedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new FileResumeDTO();
    }

    public FileResumeDTO processSignInPerCourseFile(LoadedFile loadedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new FileResumeDTO();
    }

    public FileResumeDTO processSignInsFile(LoadedFile loadedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new FileResumeDTO();
    }

}

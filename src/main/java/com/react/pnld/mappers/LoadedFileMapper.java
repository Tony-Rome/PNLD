package com.react.pnld.mappers;

import com.react.pnld.dto.FileTableResumeDTO;
import com.react.pnld.model.LoadedFile;

import java.sql.Timestamp;
import java.util.List;

public interface LoadedFileMapper {

    int insertProcessFile(LoadedFile loadedFile);

    List<LoadedFile> getLoadedFilesByStateAndTimestamps(int stateId, Timestamp initTime, Timestamp endTime);

    int updateLoadedFile(LoadedFile loadedFile);

    List<FileTableResumeDTO> getUploadedFiles();

}
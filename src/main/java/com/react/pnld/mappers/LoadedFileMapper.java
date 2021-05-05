package com.react.pnld.mappers;

import com.react.pnld.dto.FileTableResumeDTO;
import com.react.pnld.model.LoadedFile;

import java.util.List;

public interface LoadedFileMapper {

    int insertProcessFile(LoadedFile loadedFile);

    List<LoadedFile> getLoadedFilesNotProcessed();

    int updateLoadedFile(LoadedFile loadedFile);

    List<FileTableResumeDTO> getUploadedFiles();

}
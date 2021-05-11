package com.react.pnld.repo;

import com.react.pnld.dto.FileTableResumeDTO;
import com.react.pnld.mappers.LoadedFileMapper;
import com.react.pnld.model.LoadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileRepository {

    @Autowired
    private LoadedFileMapper loadedFileMapper;

    public int insertProcessFile(LoadedFile loadedFile) throws Exception {
        return loadedFileMapper.insertProcessFile(loadedFile);
    }

    public List<LoadedFile> getLoadedFilesNotProcessed() {
        return loadedFileMapper.getLoadedFilesNotProcessed();
    }

    public int updateFileLoaded(LoadedFile loadedFile) {
        return loadedFileMapper.updateLoadedFile(loadedFile);
    }

    public List<FileTableResumeDTO> getUploadedFiles() {
        return loadedFileMapper.getUploadedFiles();
    }
}

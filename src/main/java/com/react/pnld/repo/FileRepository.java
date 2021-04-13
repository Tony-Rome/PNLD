package com.react.pnld.repo;

import com.react.pnld.dto.FileTableResumeDTO;
import com.react.pnld.mappers.LoadedFileMapper;
import com.react.pnld.model.LoadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class FileRepository {

    private static final Logger logger = LoggerFactory.getLogger(FileRepository.class);

    @Autowired
    LoadedFileMapper loadedFileMapper;

    public int insertProcessFile (LoadedFile loadedFile) throws Exception {
        return this.loadedFileMapper.insertProcessFile(loadedFile);
    }

    public List<LoadedFile> getLoadedFilesByStateAndTimestamps(int stateId, Timestamp initTime, Timestamp endTime){
        return this.loadedFileMapper.getLoadedFilesByStateAndTimestamps(stateId, initTime, endTime);
    }

    public int updateFileLoaded(LoadedFile loadedFile){
        return this.loadedFileMapper.updateLoadedFile(loadedFile);
    }

    public int getFilesCount() { return loadedFileMapper.getFileCount();}

    public List<FileTableResumeDTO> getFilesUploaded(int fileNumber) {

        List<FileTableResumeDTO> filesUploaded = loadedFileMapper.getFilesUploaded(fileNumber);
        logger.info("getFilesUploaded. filesUploaded.size={}", filesUploaded.size());

        return filesUploaded;
    }
}

package com.react.pnld.repo;

import com.react.pnld.mappers.EstadoArchivoMapper;
import com.react.pnld.model.LoadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class FileRepository {

    @Autowired
    EstadoArchivoMapper estadoArchivoMapper;

    public int insertProcessFile (LoadedFile loadedFile) throws Exception {
        return estadoArchivoMapper.insertProcessFile(loadedFile);
    }

    public List<LoadedFile> getLoadedFilesByStateAndTimestamps(int stateId, Timestamp initTime, Timestamp endTime){
        return estadoArchivoMapper.getLoadedFilesByStateAndTimestamps(stateId, initTime, endTime);
    }
}

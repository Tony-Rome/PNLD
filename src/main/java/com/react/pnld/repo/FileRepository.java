package com.react.pnld.repo;

import com.react.pnld.mappers.EstadoArchivoMapper;
import com.react.pnld.model.LoadedFile;
import com.react.pnld.model.dto.EstadoArchivoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class FileRepository {

    @Autowired
    EstadoArchivoMapper estadoArchivoMapper;

    public int insertProcessFile(LoadedFile loadedFile){
        return estadoArchivoMapper.insertProcessFile(loadedFile);
    }

    public List<LoadedFile> getLoadedFilesByState(int scheduledState, Timestamp initTime, Timestamp endTime){
        return estadoArchivoMapper.getLoadedFilesByState(scheduledState, initTime, endTime);
    }

    public List<EstadoArchivoDTO> getEstadoArchivoList(){
        return estadoArchivoMapper.getEstadoArchivoList();
    }

}

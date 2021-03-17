package com.react.pnld.repo;

import com.react.pnld.mappers.EstadoArchivoMapper;
import com.react.pnld.model.LoadedFile;
import com.react.pnld.model.dto.EstadoArchivoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileRepository {

    @Autowired
    EstadoArchivoMapper estadoArchivoMapper;

    public int insertProcessFile(LoadedFile loadedFile){

        loadedFile.setStateId(1); //TODO set EstadosProcesa
        loadedFile.setProcessDate(null);
        loadedFile.setTotalRecords(0);
        loadedFile.setDuplicateRecords(0);
        loadedFile.setNewRecords(0);

        return estadoArchivoMapper.insertProcessFile(loadedFile);
    }

    public List<LoadedFile> getLoadedFilesByState(int scheduledState){
        return estadoArchivoMapper.getLoadedFilesByState(scheduledState);
    }

    public List<EstadoArchivoDTO> getEstadoArchivoList(){
        return estadoArchivoMapper.getEstadoArchivoList();
    }

}

package com.react.pnld.repo;

import com.react.pnld.mappers.EstadoArchivoMapper;
import com.react.pnld.model.EstadoArchivoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileRepository {

    @Autowired
    EstadoArchivoMapper estadoArchivoMapper;

    public EstadoArchivoDTO getEstadoArchivo(int idFileState){
        return estadoArchivoMapper.getEstadoArchivo(idFileState);
    }

}

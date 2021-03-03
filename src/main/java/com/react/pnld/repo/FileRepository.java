package com.react.pnld.repo;

import com.react.pnld.mappers.EstadoArchivoMapper;
import com.react.pnld.model.EstadoArchivoDTO;
import com.react.pnld.model.ProcesaArchivoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@Repository
public class FileRepository {

    @Autowired
    EstadoArchivoMapper estadoArchivoMapper;

    public EstadoArchivoDTO getFileState(int idFileState){
        return estadoArchivoMapper.getEstadoArchivo(idFileState);
    }

    public int insertProcessFile(ProcesaArchivoDTO procesaArchivoDTO){

        procesaArchivoDTO.setFechaCarga(OffsetDateTime.now(ZoneId.of("UTC")));
        procesaArchivoDTO.setIdPersona(0);
        procesaArchivoDTO.setIdEstado(1); //TODO set EstadosProcesa
        procesaArchivoDTO.setFechaProcesado(null);
        procesaArchivoDTO.setRegistrosTotales(0);
        procesaArchivoDTO.setRegistrosDuplicados(0);
        procesaArchivoDTO.setRegistrosNuevos(0);

        return estadoArchivoMapper.insertProcessFile(procesaArchivoDTO);
    }


}

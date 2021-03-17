package com.react.pnld.repo;

import com.react.pnld.mappers.EstadoArchivoMapper;
import com.react.pnld.model.dto.EstadoArchivoDTO;
import com.react.pnld.model.dto.ProcesaArchivoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileRepository {

    @Autowired
    EstadoArchivoMapper estadoArchivoMapper;

    public int insertProcessFile(ProcesaArchivoDTO procesaArchivoDTO){

        procesaArchivoDTO.setIdEstado(1); //TODO set EstadosProcesa
        procesaArchivoDTO.setFechaProcesado(null);
        procesaArchivoDTO.setRegistrosTotales(0);
        procesaArchivoDTO.setRegistrosDuplicados(0);
        procesaArchivoDTO.setRegistrosNuevos(0);

        return estadoArchivoMapper.insertProcessFile(procesaArchivoDTO);
    }

    public List<ProcesaArchivoDTO> getFilesProcess(int scheduledState){
        return estadoArchivoMapper.getProcessFilesScheduled(scheduledState);
    }

    public List<EstadoArchivoDTO> getEstadoArchivoList(){
        return estadoArchivoMapper.getEstadoArchivoList();
    }

}

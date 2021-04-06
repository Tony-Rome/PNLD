package com.react.pnld.repo;

import com.react.pnld.mappers.EstadoArchivoMapper;
import com.react.pnld.dto.ProcesaArchivoDTO;
import com.react.pnld.dto.TableFileDTO;
import com.react.pnld.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileRepository {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

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

    public List<TableFileDTO> getFilesUploaded(int fileNumber){

        List<TableFileDTO> filesUploaded = estadoArchivoMapper.getFilesUploaded(fileNumber);
        logger.info("getFilesUploaded. filesUploaded.size={}", filesUploaded.size());

        return filesUploaded;
    }

    public int getFilesCount(){return estadoArchivoMapper.getFileCount();}


}

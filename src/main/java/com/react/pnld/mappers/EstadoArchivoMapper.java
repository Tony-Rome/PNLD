package com.react.pnld.mappers;

import com.react.pnld.dto.ProcesaArchivoDTO;
import com.react.pnld.dto.TableFileDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EstadoArchivoMapper {

    @Insert("INSERT INTO pnld.archivo_cargado (fecha_carga, nombre, tipo_archivo, id_persona, id_estado, " +
            "fecha_procesado, registros_totales, registros_nuevos, registros_duplicados) " +
            "VALUES(#{fechaCarga},#{nombreArchivo},#{tipoArchivo},#{idPersona},#{idEstado},#{fechaProcesado}," +
            "#{registrosTotales},#{registrosNuevos},#{registrosDuplicados});")
    int insertProcessFile(ProcesaArchivoDTO procesaArchivoDTO);

    @Select("SELECT primer_nombre, nombre_archivo, tipo_archivo, fecha_carga, "+
            "registros_totales, registros_duplicados "+
            "FROM pnld.archivo_cargado LEFT JOIN pnld.persona " +
            "ON archivo_cargado.id_persona = persona.id_persona " +
            "LIMIT 10 OFFSET #{offset};")
    @Results({
            @Result(property = "loadedBy", column = "primer_nombre"),
            @Result(property = "name", column = "nombre_archivo"),
            @Result(property = "fileType", column = "tipo_archivo"),
            @Result(property = "loadedOnDateTime", column = "fecha_carga"),
            @Result(property = "totalRecords", column = "registros_totales"),
            @Result(property = "duplicateRecords", column = "registros_duplicados")
    })
    List<TableFileDTO> getFilesUploaded(int offset);

    @Select("SELECT COUNT(id_archivo) FROM pnld.archivo_cargado;")
    int getFileCount();
}
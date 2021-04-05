package com.react.pnld.mappers;

import com.react.pnld.dto.ProcesaArchivoDTO;
import com.react.pnld.dto.TableFileDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EstadoArchivoMapper {

    @Insert("INSERT INTO pnld.procesa_archivo (fecha_carga, nombre_archivo, tipo_archivo, id_persona, id_estado, " +
            "fecha_procesado, registros_totales, registros_nuevos, registros_duplicados) " +
            "VALUES(#{fechaCarga},#{nombreArchivo},#{tipoArchivo},#{idPersona},#{idEstado},#{fechaProcesado}," +
            "#{registrosTotales},#{registrosNuevos},#{registrosDuplicados});")
    int insertProcessFile(ProcesaArchivoDTO procesaArchivoDTO);

    @Select("SELECT primer_nombre, nombre_archivo, tipo_archivo, fecha_carga, "+
            "registros_totales, registros_duplicados "+
            "FROM pnld.procesa_archivo LEFT JOIN pnld.persona " +
            "ON procesa_archivo.id_persona = persona.id_persona " +
            "LIMIT 10 OFFSET #{offset};")
    @Results({
            @Result(property = "responsable", column = "primer_nombre"),
            @Result(property = "nombreArchivo", column = "nombre_archivo"),
            @Result(property = "tipoArchivo", column = "tipo_archivo"),
            @Result(property = "fechaCarga", column = "fecha_carga"),
            @Result(property = "registrosTotales", column = "registros_totales"),
            @Result(property = "registrosDuplicados", column = "registros_duplicados")
    })
    List<TableFileDTO> getFilesUploaded(int offset);

    @Select("SELECT COUNT(id_archivo) FROM pnld.procesa_archivo;")
    int getFileCount();
}
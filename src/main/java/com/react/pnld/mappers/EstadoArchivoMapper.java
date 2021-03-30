package com.react.pnld.mappers;

import com.react.pnld.model.dto.ProcesaArchivoDTO;
import com.react.pnld.model.dto.TableFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

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
            "ON procesa_archivo.id_persona = persona.id_persona;")
    @Results({
            @Result(property = "responsable", column = "primer_nombre"),
            @Result(property = "nombreArchivo", column = "nombre_archivo"),
            @Result(property = "tipoArchivo", column = "tipo_archivo"),
            @Result(property = "fechaCarga", column = "fecha_carga"),
            @Result(property = "registrosTotales", column = "registros_totales"),
            @Result(property = "registrosDuplicados", column = "registros_duplicados")
    })
    List<TableFile> getFilesUploaded(RowBounds rb);

    @Select("SELECT COUNT(id_archivo) FROM pnld.procesa_archivo;")
    int getFileCount();
}
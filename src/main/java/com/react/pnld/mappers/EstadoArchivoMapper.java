package com.react.pnld.mappers;

import com.react.pnld.model.dto.EstadoArchivoDTO;
import com.react.pnld.model.dto.ProcesaArchivoDTO;
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

    @Select("select * from pnld.procesa_archivo where id_estado = #{idEstado};")
    @Results({
            @Result(property = "idArchivo", column = "id_archivo"),
            @Result(property = "fechaCarga", column = "fecha_carga"),
            @Result(property = "nombreArchivo", column = "nombre_archivo"),
            @Result(property = "tipoArchivo", column = "tipo_archivo"),
            @Result(property = "idPersona", column = "id_persona"),
            @Result(property = "idEstado", column = "id_estado"),
            @Result(property = "fechaProcesado", column = "fecha_procesado"),
            @Result(property = "registrosTotales", column = "registros_totales"),
            @Result(property = "registrosNuevos", column = "registros_nuevos"),
            @Result(property = "registrosDuplicados", column = "registros_duplicados")
    })
    List<ProcesaArchivoDTO> getProcessFilesScheduled(int idEstado);

    @Select("select * from pnld.estado_archivo;")
    @Results({
            @Result(property = "idEstado", column = "id_estado")
    })
    List<EstadoArchivoDTO> getEstadoArchivoList();
}
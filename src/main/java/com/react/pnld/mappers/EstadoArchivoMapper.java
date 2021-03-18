package com.react.pnld.mappers;

import com.react.pnld.model.LoadedFile;
import com.react.pnld.model.dto.EstadoArchivoDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

public interface EstadoArchivoMapper {

    @Insert("INSERT INTO pnld.archivo_cargado (fecha_carga, nombre_archivo, tipo_archivo, cargado_por_id_persona, " +
            "id_estado, fecha_procesado, registros_totales, registros_nuevos, registros_duplicados) " +
            "VALUES(#{loadedDate},#{fileName},#{fileType},#{loadedByUserId},#{stateId},#{processDate}, #{totalRecords}," +
            "#{newRecords},#{duplicateRecords});")
    int insertProcessFile(LoadedFile loadedFile);

    @Select("select * from pnld.archivo_cargado " +
            "where id_estado = #{idEstado} AND fecha_carga = #{initTime} AND fecha_carga = #{endTime}")
    @Results({
            @Result(property = "idLoadedFile", column = "id_archivo"),
            @Result(property = "loadedDate", column = "fecha_carga"),
            @Result(property = "fileName", column = "nombre_archivo"),
            @Result(property = "fileType", column = "tipo_archivo"),
            @Result(property = "loadedByUserId", column = "cargado_por_id_persona"),
            @Result(property = "stateId", column = "id_estado"),
            @Result(property = "processDate", column = "fecha_procesado"),
            @Result(property = "totalRecords", column = "registros_totales"),
            @Result(property = "newRecords", column = "registros_nuevos"),
            @Result(property = "duplicateRecords", column = "registros_duplicados")
    })
    List<LoadedFile> getLoadedFilesByState(int idEstado, Timestamp initTime, Timestamp endTime);

    @Select("select * from pnld.estado_archivo;")
    @Results({
            @Result(property = "idEstado", column = "id_estado")
    })
    List<EstadoArchivoDTO> getEstadoArchivoList();
}
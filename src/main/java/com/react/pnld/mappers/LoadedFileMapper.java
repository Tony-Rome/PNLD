package com.react.pnld.mappers;

import com.react.pnld.dto.FileTableResumeDTO;
import com.react.pnld.model.LoadedFile;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

public interface LoadedFileMapper {

    @Insert("INSERT INTO pnld.archivo_cargado (fecha_carga, nombre, ubicado_en, tipo, cargado_por_admin, " +
            "id_estado, fecha_procesado, registros_totales, registros_nuevos, registros_duplicados) " +
            "VALUES(#{loadedDate},#{name}, #{storedIn}, #{type},(SELECT id FROM pnld.admin WHERE nombre_usuario = " +
            "#{loadedByAdmin}), #{stateId},#{processDate}, #{totalRecords},#{newRecords},#{duplicateRecords});")
    int insertProcessFile(LoadedFile loadedFile);

    @Select("select * from pnld.archivo_cargado " +
            "where id_estado = #{stateId} AND fecha_carga >= #{initTime} AND fecha_carga < #{endTime}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "loadedDate", column = "fecha_carga"),
            @Result(property = "name", column = "nombre"),
            @Result(property = "storedIn", column = "ubicado_en"),
            @Result(property = "type", column = "tipo"),
            @Result(property = "loadedByAdmin", column = "cargado_por_admin"),
            @Result(property = "stateId", column = "id_estado"),
            @Result(property = "processDate", column = "fecha_procesado"),
            @Result(property = "totalRecords", column = "registros_totales"),
            @Result(property = "newRecords", column = "registros_nuevos"),
            @Result(property = "duplicateRecords", column = "registros_duplicados")
    })
    List<LoadedFile> getLoadedFilesByStateAndTimestamps(int stateId, Timestamp initTime, Timestamp endTime);

    @Update("UPDATE pnld.archivo_cargado SET id_estado = #{stateId}, fecha_procesado = #{processDate}, " +
            "registros_totales = #{totalRecords}, registros_nuevos = #{newRecords}, " +
            "registros_duplicados = #{duplicateRecords} WHERE id = #{id}")
    int updateLoadedFile(LoadedFile loadedFile);

    @Select("SELECT nombre_usuario, nombre, tipo, fecha_carga, "+
            "registros_totales, registros_duplicados "+
            "FROM pnld.archivo_cargado LEFT JOIN pnld.admin " +
            "ON archivo_cargado.cargado_por_admin = admin.id " +
            "ORDER BY fecha_carga DESC " +
            "LIMIT #{limitPagination} OFFSET #{offsetPagination};")
    @Results({
            @Result(property = "loadedBy", column = "nombre_usuario"),
            @Result(property = "name", column = "nombre"),
            @Result(property = "type", column = "tipo"),
            @Result(property = "loadedOnDateTime", column = "fecha_carga"),
            @Result(property = "totalRecords", column = "registros_totales"),
            @Result(property = "duplicateRecords", column = "registros_duplicados")
    })
    List<FileTableResumeDTO> getFilesUploaded(int limitPagination, int offsetPagination);

    @Select("SELECT COUNT(id) FROM pnld.archivo_cargado;")
    int getFileCountTotal();
}
package com.react.pnld.mappers;

import com.react.pnld.dto.FileTableResumeDTO;
import com.react.pnld.model.LoadedFile;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

public interface LoadedFileMapper {

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

    List<FileTableResumeDTO> getUploadedFiles();

}
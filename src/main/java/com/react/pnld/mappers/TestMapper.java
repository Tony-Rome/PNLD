package com.react.pnld.mappers;

import com.react.pnld.model.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface TestMapper {

    @Select("SELECT * FROM pnld.test WHERE id_docente=#{teacherId} AND tipo=#{typeTest};")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "teacherId", column = "id_docente"),
            @Result(property = "loadedFileId", column = "id_archivo_fuente"),
            @Result(property = "type", column = "tipo"),
            @Result(property = "state", column = "estado"),
            @Result(property = "initDate", column = "fecha_inicio"),
            @Result(property = "endDate", column = "fecha_fin"),
            @Result(property = "duration", column = "tiempo_requerido"),
            @Result(property = "score", column = "calificacion")})
    Test getTeacherTest(int teacherId, String typeTest);

    @Insert("INSERT INTO pnld.test (id_docente, id_archivo_fuente, tipo, estado, fecha_inicio, fecha_fin, tiempo_requerido," +
            "calificacion) VALUES (#{teacherId}, #{loadedFileId}, #{type}, #{state}, #{initDate}, #{endDate}, #{duration}, " +
            "#{score});")
    int insertTest(Test test);
}

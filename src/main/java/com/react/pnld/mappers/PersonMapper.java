package com.react.pnld.mappers;

import com.react.pnld.model.Teacher;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface PersonMapper {

    @Select("SELECT * FROM pnld.docente where correo=#{email}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "idPerson", column = "id_persona"),
            @Result(property = "email", column = "correo")})
    Teacher getTeacher(String email);
}

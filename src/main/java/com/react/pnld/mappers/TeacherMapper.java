package com.react.pnld.mappers;

import com.react.pnld.model.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface TeacherMapper {

    @Select("SELECT * FROM pnld.docente where rut=#{rut} AND correo=#{email}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "nombre"),
            @Result(property = "paternalLastName", column = "apellido_paterno"),
            @Result(property = "maternalLastName", column = "apellido_materno"),
            @Result(property = "rut", column = "rut"),
            @Result(property = "email", column = "correo"),
            @Result(property = "genderId", column = "id_genero")})
    Teacher getTeacher(String rut, String email);

    @Insert("INSERT INTO pnld.docente (id, nombre, apellido_paterno, apellido_materno, rut," +
            "correo, id_genero) VALUES (#{id}, #{firstName},#{lastName},#{motherLastName},#{rut}," +
            "#{email},#{genderId});")
    int insertTeacher(Teacher teacher);

    @Select("SELECT nextval(pg_get_serial_sequence('pnld.docente', 'id'));")
    int getNextTeacherId();
}

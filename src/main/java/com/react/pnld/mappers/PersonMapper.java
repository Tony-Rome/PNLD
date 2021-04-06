package com.react.pnld.mappers;

import com.react.pnld.model.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface PersonMapper {

    @Select("SELECT * FROM pnld.persona where rut=#{rut} AND correo=#{email}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "primer_nombre"),
            @Result(property = "secondName", column = "segundo_nombre"),
            @Result(property = "lastName", column = "apellido_paterno"),
            @Result(property = "motherLastName", column = "apellido_materno"),
            @Result(property = "rut", column = "rut"),
            @Result(property = "email", column = "correo"),
            @Result(property = "genderId", column = "id_genero")})
    Person getPerson(String rut, String email);

    @Insert("INSERT INTO pnld.persona (primer_nombre, segundo_nombre, apellido_paterno, apellido_materno, rut," +
            "correo, id_genero) VALUES (#{firstName},#{secondName},#{lastName},#{motherLastName},#{rut}," +
            "#{email},#{genderId});")
    int insertPerson(Person person);

    @Insert("INSERT INTO pnld.docente select id FROM pnld.persona WHERE rut=#{personRut};")
    int insertTeacherByPersonRut(String personRut);
}

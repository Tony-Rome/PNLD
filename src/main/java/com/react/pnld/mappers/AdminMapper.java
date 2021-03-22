package com.react.pnld.mappers;

import com.react.pnld.model.Admin;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper {

    @Select("SELECT * FROM pnld.pnld.admin WHERE nombre = #{username}")
    @Results({
            @Result(property = "idAdmin", column = "id_admin"),
            @Result(property = "username", column = "nombre"),
            @Result(property = "password", column = "clave")
    })
    Admin findByUsername(String username);
}

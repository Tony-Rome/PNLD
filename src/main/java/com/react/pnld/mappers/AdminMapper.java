package com.react.pnld.mappers;

import com.react.pnld.authentication.Admin;
import com.react.pnld.model.EstadoArchivoDTO;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper {

    @Select("SELECT * FROM admin WHERE username = #{username}")
    Admin findByUsername(String username);
}

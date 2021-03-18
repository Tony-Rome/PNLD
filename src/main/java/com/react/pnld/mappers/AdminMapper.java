package com.react.pnld.mappers;

import com.react.pnld.model.Admin;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper {

    @Select("SELECT * FROM pnld.pnld.admin WHERE nombre = #{nombre}")
    Admin findByUsername(String nombre);
}

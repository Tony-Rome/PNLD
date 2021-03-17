package com.react.pnld.mappers;

import com.react.pnld.authentication.Admin;
import com.react.pnld.model.EstadoArchivoDTO;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper {

    /**
     *  Modelo de datos Admin tiene las siguientes columnas:
     *    Column  |       Type        | Collation | Nullable |              Default
     * ----------+-------------------+-----------+----------+-----------------------------------
     *  id       | integer           |           | not null | nextval('admin_id_seq'::regclass)
     *  username | character varying |           | not null |
     *  email    | character varying |           | not null |
     *  password | character varying |           | not null |
     *  role     | character varying |           | not null |
     *
     *  valor del campo role debe ser ROLE_ADMIN
     * */
    @Select("SELECT * FROM pnld.pnld.admin WHERE username = #{username}")
    Admin findByUsername(String username);
}

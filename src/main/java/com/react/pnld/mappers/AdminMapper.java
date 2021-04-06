package com.react.pnld.mappers;

import com.react.pnld.model.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {

    Admin findByUsername(@Param(value = "username") String username);
}

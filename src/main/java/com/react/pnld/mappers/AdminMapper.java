package com.react.pnld.mappers;

import com.react.pnld.model.Admin;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper {

    Admin findByUsername(String username);
}

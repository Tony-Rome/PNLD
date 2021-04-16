package com.react.pnld.mappers;

import com.react.pnld.model.Admin;

public interface AdminMapper {

    Admin findByUsername(String username);
}

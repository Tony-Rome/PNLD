package com.react.pnld.mappers;

import com.react.pnld.model.School;

public interface SchoolMapper {

    int getNextSchoolId();
    School getSchoolByName(String name);
    int insertSchool(School school);
    School getSchool(String name, int rbd);
}

package com.react.pnld.mappers;

import com.react.pnld.model.School;

public interface SchoolMapper {

    School getSchoolWhereName(String name);

    int insertSchool(School school);

    int updateSchool(School school);

    School getSchoolWhereRbd(int rbd);
}

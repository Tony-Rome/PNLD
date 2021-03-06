package com.react.pnld.repo;

import com.react.pnld.mappers.SchoolMapper;
import com.react.pnld.model.School;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SchoolRepository {
    private static final Logger logger = LoggerFactory.getLogger(SchoolRepository.class);

    @Autowired
    SchoolMapper schoolMapper;

    public Optional<School> getSchoolWhereName(String name) {
        return Optional.ofNullable(schoolMapper.getSchoolWhereName(name));
    }

    public int insertSchool(School school) {
        return schoolMapper.insertSchool(school);
    }

    public int updateSchool(School school) {
        logger.info("updateSchool. school={}", school);
        return schoolMapper.updateSchool(school);
    }

    public Optional<School> getSchoolWhereRbd(int rbd) {
        return Optional.ofNullable(schoolMapper.getSchoolWhereRbd(rbd));
    }

    public Optional<School> getSchoolNotSpecified(){
        return this.getSchoolWhereRbd(0);
    }

}

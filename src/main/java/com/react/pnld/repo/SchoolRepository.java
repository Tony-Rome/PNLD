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

    public int getNextSchoolId(){
        return schoolMapper.getNextSchoolId();
    }

    public Optional<School> getSchoolByName(String name){
        return Optional.ofNullable(schoolMapper.getSchoolByName(name));
    }

    public int insertSchool(School school){
        logger.info("insertSchool. school={}", school);
        return schoolMapper.insertSchool(school);
    }
}

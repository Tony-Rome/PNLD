package com.react.pnld.repo;

import com.react.pnld.mappers.Gendermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenderRepository {

    @Autowired
    Gendermapper gendermapper;

    public int getGenderIdByType(String type) {
        return gendermapper.getGenderIdByType(type);
    }
}

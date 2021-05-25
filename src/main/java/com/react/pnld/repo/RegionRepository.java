package com.react.pnld.repo;

import com.react.pnld.mappers.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RegionRepository {

    @Autowired
    RegionMapper regionMapper;

    public Integer getRegionIdByName(String name) {
        return regionMapper.getRegionIdByName(name);
    }
}

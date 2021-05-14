package com.react.pnld.mappers;

import org.apache.ibatis.annotations.Param;

public interface RegionMapper {

    int getRegionIdByName(@Param("name") String name);
}

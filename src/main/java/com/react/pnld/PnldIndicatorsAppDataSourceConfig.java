package com.react.pnld;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
@MapperScan("com.react.pnld.mappers")
public class PnldIndicatorsAppDataSourceConfig {

    @Autowired
    private DataSource dataSource;



}

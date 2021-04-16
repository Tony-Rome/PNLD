package com.react.pnld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PnldIndicatorsAppDataSourceConfig {

    @Autowired
    private DataSource dataSource;

}

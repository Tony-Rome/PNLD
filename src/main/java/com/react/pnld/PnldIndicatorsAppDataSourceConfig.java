package com.react.pnld;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.react.pnld.mappers")
public class PnldIndicatorsAppDataSourceConfig {

    @Autowired
    private DataSource dataSource;

    @Bean(name = "platformTransactionManager")
    public PlatformTransactionManager platformTransactionManager(){
        return new DataSourceTransactionManager(this.dataSource);
    }

    @Bean()
    @Primary
    public SqlSessionFactory sqlSessionFactoryPNLD() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(this.dataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("maps.postgres/*.xml");
        sessionFactoryBean.setMapperLocations(resources);
        return sessionFactoryBean.getObject();
    }

   /* @Bean(name = "estadoArchivoMapperFactoryBean")
    public MapperFactoryBean<EstadoArchivoMapper> estadoArchivoMapperFactoryBean() throws Exception {
        MapperFactoryBean<EstadoArchivoMapper> mapperFactoryBean = new MapperFactoryBean<EstadoArchivoMapper>();
        mapperFactoryBean.setMapperInterface(EstadoArchivoMapper.class);
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactoryPNLD());
        return mapperFactoryBean;
    }*/



}

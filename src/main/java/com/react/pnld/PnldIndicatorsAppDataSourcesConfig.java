package com.react.pnld;

import com.react.pnld.mappers.EstadoArchivoMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class PnldIndicatorsAppDataSourcesConfig {

    @Autowired
    private DataSource dataSource;

    @Bean(name = "platformTransactionManager")
    public PlatformTransactionManager platformTransactionManager(){
        return new DataSourceTransactionManager(this.dataSource);
    }

    @Bean(name = "sqlSessionFactoryPNLD")
    @Primary
    public SqlSessionFactory sqlSessionFactoryPNLD() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(this.dataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("maps/*.xml");
        sessionFactoryBean.setMapperLocations(resources);
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "estadoArchivoMapperFactoryBean")
    public MapperFactoryBean<EstadoArchivoMapper> estadoArchivoMapperFactoryBean() throws Exception {
        MapperFactoryBean<EstadoArchivoMapper> mapperFactoryBean = new MapperFactoryBean<EstadoArchivoMapper>();
        mapperFactoryBean.setMapperInterface(EstadoArchivoMapper.class);
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactoryPNLD());
        return mapperFactoryBean;
    }
}

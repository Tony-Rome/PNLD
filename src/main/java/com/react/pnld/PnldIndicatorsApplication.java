package com.react.pnld;

import com.react.pnld.mappers.EstadoArchivoMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@SpringBootApplication
@ConfigurationPropertiesScan("com.react.pnld.model")
@MapperScan("com.react.pnld.mappers")
public class PnldIndicatorsApplication {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
    }

    public static void main(String[] args) {
        SpringApplication.run(PnldIndicatorsApplication.class, args);
    }

}

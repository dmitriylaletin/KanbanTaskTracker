package com.eisgroup.tasktracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 10:00
 */
@Configuration
@PropertySource("classpath:db.properties")
public class DbConfig {
    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driverClass"));
        dataSource.setUrl("jdbc:mysql://" +
                environment.getProperty("db.host") +
                ":" +
                environment.getProperty("db.port") +
                "/" +
                environment.getProperty("db.dbname"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }
}

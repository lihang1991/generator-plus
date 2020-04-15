package com.github.generator.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.generator.dataSource.properties.DataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DynamicDataSource dynamicDataSource(DataSourceProperties dataSourceProperties) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //默认数据源
        DruidDataSource defaultDataSource = DynamicDataSourceFactory.buildDruidDataSource(dataSourceProperties);
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
        Map<Object, Object> target = new HashMap<>();
        target.put(DataSourceSelect.MASTER, defaultDataSource);

//        DataSourceProperties dataSourceProperties2 = new DataSourceProperties();
//        dataSourceProperties2.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSourceProperties2.setUrl("jdbc:mysql://20.21.1.127:3306/yqgk?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
//        dataSourceProperties2.setUsername("root");
//        dataSourceProperties2.setPassword("Mysql123456.");
//        DruidDataSource druidDataSource = DynamicDataSourceFactory.buildDruidDataSource(dataSourceProperties2);
//        target.put(DataSourceSelect.GENERATOR, druidDataSource);

        dynamicDataSource.setTargetDataSources(target);
        dynamicDataSource.afterPropertiesSet();
        return dynamicDataSource;
    }
}

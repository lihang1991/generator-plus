package com.github.generator.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.generator.dao.*;
import com.github.generator.dataSource.DataSourceSelect;
import com.github.generator.dataSource.DynamicDataSource;
import com.github.generator.dataSource.DynamicDataSourceFactory;
import com.github.generator.dataSource.properties.DataSourceProperties;
import com.github.generator.utils.ApplicationContextHelp;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private ApplicationContextHelp applicationContextHelp;
    @Autowired
    private MySQLGeneratorDao mySQLGeneratorDao;
    @Autowired
    private OracleGeneratorDao oracleGeneratorDao;
    @Autowired
    private SQLServerGeneratorDao sqlServerGeneratorDao;
    @Autowired
    private PostgreSQLGeneratorDao postgreSQLGeneratorDao;

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @RequestMapping("change")
    public Object change(@RequestParam String database) {
        String beanName =  "generatorDao";
        Object bean2 = applicationContextHelp.getBean(beanName);
        BeanDefinition beanDefinition = applicationContextHelp.getBeanDefinition("mySQLGeneratorDao");
        Class generatorDao = null;
        if("mysql".equalsIgnoreCase(database)){
            beanDefinition = applicationContextHelp.getBeanDefinition("mySQLGeneratorDao");
        }else if("oracle".equalsIgnoreCase(database)){
            beanDefinition = applicationContextHelp.getBeanDefinition("oracleGeneratorDao");
        }else if("sqlserver".equalsIgnoreCase(database)){
            beanDefinition = applicationContextHelp.getBeanDefinition("sqlServerGeneratorDao");
        }else if("postgresql".equalsIgnoreCase(database)){
            beanDefinition = applicationContextHelp.getBeanDefinition("postgreSQLGeneratorDao");
        }
        applicationContextHelp.registerBeanDefinition(beanDefinition, beanName);
        Object bean = applicationContextHelp.getBean(beanName);
        GeneratorDao generatorDao1 = (GeneratorDao)bean;
        return bean.getClass().toString();
    }

    @RequestMapping("current")
    public Object current() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setDriverClassName("com.mysql.jdbc.Driver");
        dataSourceProperties.setUrl("jdbc:mysql://20.21.1.127:3306/yqgk?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        dataSourceProperties.setUsername("root");
        dataSourceProperties.setPassword("Mysql123456.");
        DruidDataSource druidDataSource = DynamicDataSourceFactory.buildDruidDataSource(dataSourceProperties);
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceSelect.GENERATOR, druidDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSource);
        dynamicDataSource.afterPropertiesSet();
        return "success";
    }

}

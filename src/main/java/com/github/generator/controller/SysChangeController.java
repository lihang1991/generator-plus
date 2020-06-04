package com.github.generator.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.generator.dataSource.DataSourceSelect;
import com.github.generator.dataSource.DynamicDataSource;
import com.github.generator.dataSource.DynamicDataSourceFactory;
import com.github.generator.dataSource.properties.DataSourceProperties;
import com.github.generator.entity.DatasourceEntity;
import com.github.generator.service.SysDatasourceService;
import com.github.generator.utils.ApplicationContextHelp;
import com.github.generator.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("sys/change")
public class SysChangeController {

    @Autowired
    private ApplicationContextHelp applicationContextHelp;

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Autowired
    private SysDatasourceService sysDatasourceService;

    /**
     * 修改数据库链接dao bean
     * @param database
     */
    public void changeDaoBean(String database) {
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
    }

    @RequestMapping("current")
    public R current(@RequestParam String id) {
        DatasourceEntity datasourceEntity = sysDatasourceService.getById(id);
        if (Objects.isNull(datasourceEntity)) {
            R.error("该数据不存在");
        }
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
//        dataSourceProperties.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSourceProperties.setUrl("jdbc:mysql://20.21.1.127:3306/yqgk?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
//        dataSourceProperties.setUsername("root");
//        dataSourceProperties.setPassword("Mysql123456.");
        dataSourceProperties.setDriverClassName(datasourceEntity.getDriverClassName());
        dataSourceProperties.setUrl(datasourceEntity.getUrl());
        dataSourceProperties.setUsername(datasourceEntity.getUsername());
        dataSourceProperties.setPassword(datasourceEntity.getPassword());
        DruidDataSource druidDataSource = DynamicDataSourceFactory.buildDruidDataSource(dataSourceProperties);
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceSelect.GENERATOR, druidDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSource);
        dynamicDataSource.afterPropertiesSet();
        changeDaoBean(datasourceEntity.getName());
        return R.ok();
    }

}

package com.github.generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 数据源配置
 * @author lih
 */
@Data
@Accessors(chain = true)
@TableName("sys_datasource")
public class DatasourceEntity {

    private String id;

    private String name;
    private String driverClassName;
    private String url;
    private String username;
    private String password;

}

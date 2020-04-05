package com.github.generator.entity.enums;


/**
 * 数据类型
 * @author lih
 * @see {mysql、oracle、sqlserver、postgresql}
 */
public enum DataSourceTypeEnum {

    MYSQL("mysql"),
    ORACLE("oracle"),
    SQLSERVER("sqlserver"),
    POSTGRESQL("postgresql"),
    ;

    private String dataSource;

    DataSourceTypeEnum(String dataSource) {
        this.dataSource = dataSource;
    }
}

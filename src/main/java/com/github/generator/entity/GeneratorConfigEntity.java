package com.github.generator.entity;


import java.io.Serializable;

/**
 * 代码生成规则配置
 * @author lih
 */
public class GeneratorConfigEntity implements Serializable {

    /** ID */
    private String id;

    /**　数据源类型 */
    private String dataSourceType;
    /** 数据源链接url */
    private String dataSourceUrl;
    /** 数据源帐号 */
    private String userName;
    /** 数据源密码 */
    private String password;
    /**　是否使用jdk8的localdatetime */
    private boolean useLocalDate;
    /** 是否使用lombok */
    private boolean useLombok;
    /** 包名 */
    private String packageName;
    /**　模块名 */
    private String moduleName;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataSourceUrl() {
        return dataSourceUrl;
    }

    public void setDataSourceUrl(String dataSourceUrl) {
        this.dataSourceUrl = dataSourceUrl;
    }

    public String getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public boolean isUseLocalDate() {
        return useLocalDate;
    }

    public void setUseLocalDate(boolean useLocalDate) {
        this.useLocalDate = useLocalDate;
    }

    public boolean isUseLombok() {
        return useLombok;
    }

    public void setUseLombok(boolean useLombok) {
        this.useLombok = useLombok;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

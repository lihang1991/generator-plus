package com.github.generator.dataSource;

public class DbContextHolder {

    private final static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源
     */
    public static void setDbType(String dbTypeEnum) {
        contextHolder.set(dbTypeEnum);
    }

    /**
     * 取得当前数据源
     */
    public static String getDbType() {
        return (String) contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        contextHolder.remove();
    }
}

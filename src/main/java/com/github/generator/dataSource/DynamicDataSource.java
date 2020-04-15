package com.github.generator.dataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected Object determineCurrentLookupKey() {
        logger.debug(">>>>>>>>>：{}", DbContextHolder.getDbType());
        return DbContextHolder.getDbType();
    }
}

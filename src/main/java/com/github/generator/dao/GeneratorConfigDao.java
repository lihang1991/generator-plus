package com.github.generator.dao;

import com.github.generator.entity.GeneratorConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GeneratorConfigDao {

    GeneratorConfigEntity selectOne();

    int update(@Param("entity") GeneratorConfigEntity generatorConfigEntity);
}

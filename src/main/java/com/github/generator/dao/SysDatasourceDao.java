package com.github.generator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.generator.entity.DatasourceEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lih
 * @date 2020-04-15 下午9:44
 **/
@Mapper
public interface SysDatasourceDao extends BaseMapper<DatasourceEntity> {
}

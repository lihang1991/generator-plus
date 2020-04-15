/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.github.generator.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.generator.dao.GeneratorDao;
import com.github.generator.dataSource.DataSourceSelect;
import com.github.generator.dataSource.anno.DataSource;
import com.github.generator.utils.ApplicationContextHelp;
import com.github.generator.utils.GenUtils;
import com.github.generator.utils.PageUtils;
import com.github.generator.utils.Query;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class SysGeneratorService {
	//@Autowired
	private GeneratorDao generatorDao;

	@Autowired
	ApplicationContextHelp applicationContextHelp;

	@DataSource(value = DataSourceSelect.GENERATOR)
	public PageUtils queryList(Query query) {
		GeneratorDao generatorDao = (GeneratorDao)applicationContextHelp.getBean("generatorDao");
//		Page<?> page = PageHelper.startPage(query.getPage(), query.getLimit());
		Page page = new Page(query.getPage(), query.getLimit());
		List<Map<String, Object>> list = generatorDao.queryList(page, query);

		return null;// new PageUtils(list, (int)page.getTotal(), query.getLimit(), query.getPage());
	}

	public Map<String, String> queryTable(String tableName) {
		return generatorDao.queryTable(tableName);
	}

	public List<Map<String, String>> queryColumns(String tableName) {
		return generatorDao.queryColumns(tableName);
	}

	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		for(String tableName : tableNames){
			//查询表信息
			Map<String, String> table = queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = queryColumns(tableName);
			//生成代码
			GenUtils.generatorCode(table, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}
}

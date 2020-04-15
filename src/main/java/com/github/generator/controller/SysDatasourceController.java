package com.github.generator.controller;

import com.github.generator.entity.DatasourceEntity;
import com.github.generator.service.SysDatasourceService;
import com.github.generator.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据源配置
 * @author lih
 */
@RestController
@RequestMapping("datasource")
public class SysDatasourceController {

    @Autowired
    private SysDatasourceService sysDatasourceService;

    @RequestMapping("list")
    public R list() {
        List<DatasourceEntity> list = sysDatasourceService.list();
        return R.ok().put("list", list);
    }

    @GetMapping("test/save")
    public R save() {
        DatasourceEntity datasourceEntity = new DatasourceEntity();
        datasourceEntity.setId("1")
                .setDriverClassName("com.mysql.jdbc.Driver")
                .setName("mysql")
                .setPassword("Mysql123456.")
                .setUrl("jdbc:mysql://20.21.1.127:3306/yqgk?useUnicode=true&characterEncoding=UTF-8&useSSL=false")
                .setUsername("root");
        this.sysDatasourceService.save(datasourceEntity);
        return R.ok();
    }

    @RequestMapping("saveOrUpdate")
    public R saveOrUpdate(@RequestBody DatasourceEntity datasourceEntity) {
        return null;
    }

    @RequestMapping("delete")
    public R delete(@RequestParam String id) {
        return null;
    }
}

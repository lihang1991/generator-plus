package com.github.generator.controller;

import com.github.generator.entity.DatasourceEntity;
import com.github.generator.utils.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据源配置
 * @author lih
 */
@RestController
@RequestMapping("datasource")
public class SysDatasourceController {

    @RequestMapping("list")
    public R list() {
        return null;
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

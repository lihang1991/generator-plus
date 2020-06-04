package com.github.generator.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.generator.entity.DatasourceEntity;
import com.github.generator.service.SysDatasourceService;
import com.github.generator.utils.PageUtils;
import com.github.generator.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 数据源配置
 * @author lih
 */
@RestController
@RequestMapping("sys/datasource")
public class SysDatasourceController {

    @Autowired
    private SysDatasourceService sysDatasourceService;

    @RequestMapping("list")
    public R list(@RequestParam Map<String, Object> params) {
        //分页参数
        Integer current = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        Page page = new Page(current, limit);
        QueryWrapper<DatasourceEntity> queryWrapper = new QueryWrapper<>();
        Page<DatasourceEntity> list = sysDatasourceService.page(page, queryWrapper);
        return R.ok().put("page", new PageUtils(list.getRecords(), (int)page.getTotal(), limit, current));
    }

    @RequestMapping("all")
    public R all() {
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
//        DatasourceEntity datasourceEntity = new DatasourceEntity();
        datasourceEntity.setId("1")
                .setDriverClassName("com.mysql.jdbc.Driver")
                .setName("mysql")
                .setPassword("Mysql123456.")
                .setUrl("jdbc:mysql://20.21.1.127:3306/yqgk?useUnicode=true&characterEncoding=UTF-8&useSSL=false")
                .setUsername("root");
        this.sysDatasourceService.save(datasourceEntity);
        return R.ok();
    }

    @RequestMapping("delete")
    public R delete(@RequestParam String id) {
        return null;
    }
}

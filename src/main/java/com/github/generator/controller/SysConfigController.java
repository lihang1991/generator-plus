package com.github.generator.controller;

import com.github.generator.entity.GeneratorConfigEntity;
import com.github.generator.service.GeneratorConfigService;
import com.github.generator.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局属性配置
 * @author lih
 */
@Controller
@RequestMapping(value = "sys/config")
public class SysConfigController {

    @Autowired
    private GeneratorConfigService generatorConfigService;

    @ResponseBody
    @RequestMapping(value = "get")
    public Object get() {
        GeneratorConfigEntity generatorConfigEntity = generatorConfigService.get();
        return R.ok().put("data", generatorConfigEntity);
    }

    @ResponseBody
    @RequestMapping(value = "save")
    public Object save(@RequestBody GeneratorConfigEntity configEntity) {
        generatorConfigService.update(configEntity);
        return R.ok();
    }
}

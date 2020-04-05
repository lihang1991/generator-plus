package com.github.generator.service;

import com.github.generator.dao.GeneratorConfigDao;
import com.github.generator.entity.GeneratorConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneratorConfigService {

    @Autowired
    private GeneratorConfigDao generatorConfigDao;

    public GeneratorConfigEntity get() {
        return generatorConfigDao.selectOne();
    }

    public void update(GeneratorConfigEntity generatorConfigEntity) {
        generatorConfigDao.update(generatorConfigEntity);
    }
}

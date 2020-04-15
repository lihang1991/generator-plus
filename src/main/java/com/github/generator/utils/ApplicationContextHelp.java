package com.github.generator.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHelp implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Object getBean(String beanName) {
        return applicationContext != null ? applicationContext.getBean(beanName):null;
    }

    public BeanDefinition getBeanDefinition(String beanName) {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
        return defaultListableBeanFactory.getBeanDefinition(beanName);
    }

    public void register(Class clazz, String name) {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
        if (defaultListableBeanFactory.containsBean(name)) {
            defaultListableBeanFactory.removeBeanDefinition(name);
        }
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        defaultListableBeanFactory.registerBeanDefinition(name, beanDefinitionBuilder.getRawBeanDefinition());

    }

    public void registerBeanDefinition(BeanDefinition clazz, String name) {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
        if (defaultListableBeanFactory.containsBean(name)) {
            defaultListableBeanFactory.removeBeanDefinition(name);
        }
        defaultListableBeanFactory.registerBeanDefinition(name, clazz);

    }
}

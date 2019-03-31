package com.yinya.beanregistrar;

import com.yinya.bean.Person;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        // 可以判断容器是否包含某组件id

        RootBeanDefinition beanDefinition = new RootBeanDefinition(Person.class);
        // 指定bean的id 注册进容器中
        registry.registerBeanDefinition("registarPerson", beanDefinition);
    }
}

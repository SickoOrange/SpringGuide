package com.yinya.beanregistrar;

import com.yinya.bean.Person;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MacCondition implements Condition {

    // context 判断条件使用的上下问环境
    // metadata 当前注解的注释信息
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // ioc 当前bean工厂
        ConfigurableListableBeanFactory factory = context.getBeanFactory();

        // 类加载器
        ClassLoader classLoader = context.getClassLoader();

        // 获取环境信息
        Environment environment = context.getEnvironment();

        // 获取beanDefinitionRegistry bean定义的注册类 可以在这里查询或者注册bean定义
        BeanDefinitionRegistry registry = context.getRegistry();

        String os = environment.getProperty("os.name");

        if (os.contains("Mac")) {
            return true;
        }
        return false;
    }
}

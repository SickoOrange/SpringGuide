package com.yinya.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCondition implements Condition {

    // context 判断条件使用的上下问环境
    // metadata 当前注解的注释信息
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        // 获取环境信息
        Environment environment = context.getEnvironment();


        String os = environment.getProperty("os.name");

        if (os.contains("Windows")) {
            return true;
        }
        return false;
    }
}

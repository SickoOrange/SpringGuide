package com.yinya.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    // 在Bean实例创建好之后，但是在任何初始化方法（custom init method）调用之前调用
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessBeforeInitialization: bean name:--->" + s);
        // bean为刚创建的对象，也可以进行包装
        return o;
    }

    // 在在Bean实例创建好之后，但是在任何初始化方法（custom init method）调用之后调用
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessAfterInitialization: bean name:--->" + s);
        return o;
    }
}

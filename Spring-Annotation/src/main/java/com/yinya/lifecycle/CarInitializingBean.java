package com.yinya.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class CarInitializingBean implements InitializingBean, DisposableBean {
    public void afterPropertiesSet() throws Exception {
        System.out.println("bean被创建完成，属性赋值完成之后，开始调用初始化方法");
    }

    public void destroy() throws Exception {
        System.out.println("bean 被销毁了");
    }
}

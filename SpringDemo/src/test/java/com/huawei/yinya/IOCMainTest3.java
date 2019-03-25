package com.huawei.yinya;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCMainTest3 {

    ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring3.xml");

    /**
     * 使用ref来获取引用对象
     */
    @Test
    public void test1() {
        ctx.close();
    }


}
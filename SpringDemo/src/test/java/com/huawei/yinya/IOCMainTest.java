package com.huawei.yinya;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class IOCMainTest {


    /**
     * 从容器中拿到 id=macbookpro 的组建
     */
    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        Laptop macbookpro = (Laptop) ctx.getBean("macbookpro");
        System.out.println(macbookpro);
    }


}
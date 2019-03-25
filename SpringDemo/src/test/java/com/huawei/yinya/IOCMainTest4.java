package com.huawei.yinya;

import com.huawei.yinya.xml.ComplexLaptop;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCMainTest4 {

    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring4.xml");

    @Test
    public void test(){
        ComplexLaptop bean = ctx.getBean(ComplexLaptop.class);
        System.out.println(bean);
    }




}
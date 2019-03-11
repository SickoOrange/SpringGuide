package com.huawei.yinya;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCMainTest2 {

    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring2.xml");

    /**
     * 使用ref来获取引用对象
     */
    @Test
    public void test1() {
        ComplexLaptop macbookpro = (ComplexLaptop) ctx.getBean("macbookpro1");
        System.out.println(macbookpro);
    }

    /**
     * 使用bean'标签来创建引用对象
     */
    @Test
    public void test2() {
        ComplexLaptop macbookpro = (ComplexLaptop) ctx.getBean("macbookpro2");
        System.out.println(macbookpro);
    }


    /**
     * 为list属性赋值
     */
    @Test
    public void test3() {
        ComplexLaptop macbookpro = (ComplexLaptop) ctx.getBean("macbookpro3");
        System.out.println(macbookpro);
    }


    /**
     * 为map属性赋值
     */
    @Test
    public void test4() {
        ComplexLaptop macbookpro = (ComplexLaptop) ctx.getBean("macbookpro4");
        System.out.println(macbookpro.getMap());
    }


    /**
     * 为map属性赋值
     */
    @Test
    public void test5() {
        ComplexLaptop macbookpro = (ComplexLaptop) ctx.getBean("macbookpro5");
        System.out.println(macbookpro.getProperties());
    }


}
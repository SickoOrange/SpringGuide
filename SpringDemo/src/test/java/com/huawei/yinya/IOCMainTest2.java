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

    /**
     * 为级联属性赋值
     */
    @Test
    public void test6() {
        ComplexLaptop macbookpro = (ComplexLaptop) ctx.getBean("macbookpro6");
        Master master = (Master) ctx.getBean("master");
        System.out.println(macbookpro.getMaster());
        System.out.println(master);
    }

    /**
     * 属性的继承
     */
    @Test
    public void test7() {
        Laptop laptopcopy1 = (Laptop) ctx.getBean("laptopcopy1");
        Laptop laptopcopy2 = (Laptop) ctx.getBean("laptopcopy2");
        System.out.println(laptopcopy1);
        System.out.println(laptopcopy2);
    }

    /**
     * 工厂方法
     */
    @Test
    public void test8() {
        Laptop xiaomi = (Laptop) ctx.getBean("xiaomi");
        System.out.println(xiaomi);

        Laptop huawei = (Laptop) ctx.getBean("huawei");
        System.out.println(huawei);

        Laptop factoryBeanLaptop = (Laptop) ctx.getBean("factoryBeanLaptop");
        System.out.println(factoryBeanLaptop);
    }


}
package com.huawei.yinya;

import com.huawei.yinya.xml.Laptop;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCMainTest {

    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

    /**
     * 从容器中拿到 通过Bean的id来获取
     */
    @Test
    public void test1() {
        Laptop macbookpro = (Laptop) ctx.getBean("macbookpro");
        System.out.println(macbookpro);
    }

    /**
     * 从容器中拿到 通过Bean的类型来获取
     */
    @Test
    public void test2() {
        Laptop macbookpro = (Laptop) ctx.getBean(Laptop.class);
        System.out.println(macbookpro);
    }

    /**
     * 从容器中拿到 通过Bean的id跟类型来获取bean class相同的多个不同实例
     */
    @Test
    public void test3() {
        Laptop macbookpro = (Laptop) ctx.getBean("macbookpro", Laptop.class);
        Laptop lenovo = (Laptop) ctx.getBean("lenovo", Laptop.class);
        System.out.println(macbookpro);
        System.out.println(lenovo);
    }

    @Test
    public void test4() {
        Laptop acer = (Laptop) ctx.getBean("acer");
        System.out.println(acer);
    }


}
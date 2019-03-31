package com.yinya.test;

import com.yinya.bean.Person;
import com.yinya.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        // 按照类型来从IOC容器获取person
        Person person = context.getBean(Person.class);
        System.out.println(person);

        // Person这个组件在IOC的name
        String[] names = context.getBeanNamesForType(Person.class);
        for (String name : names) {
            System.out.println(name);
        }
        // name = person
    }
}

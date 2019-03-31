package com.yinya.test;

import com.yinya.bean.Person;
import com.yinya.config.MainConfig;
import com.yinya.config.MainConfig2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

public class MainConfig2test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);

        // 按照id获取person 单例
//        Object person = context.getBean("person");
//        Object person2 = context.getBean("person");
//        System.out.println(person == person2);


        Map<String, Person> beans = context.getBeansOfType(Person.class);
        System.out.println(beans);
        ConfigurableEnvironment environment = context.getEnvironment();
        String os = environment.getProperty("os.name");
        System.out.println(os);

        // 获取工厂bean
        Object myFactoryBean = context.getBean("&myFactoryBean");
        System.out.println(myFactoryBean);

    }
}

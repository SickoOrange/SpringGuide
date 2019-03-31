package com.yinya.config;


import com.yinya.bean.ImportBean;
import com.yinya.bean.Person;
import com.yinya.beanregistrar.*;
import org.springframework.context.annotation.*;

@Configuration
@Import({ImportBean.class, MyImportSelector.class, MyBeanDefinitionRegistrar.class})
public class MainConfig2 {

    // prototype
    // singleton
    @Scope(value = "prototype")
    // @Bean("person")
    public Person person() {
        return new Person("yinya", "50");
    }

    @Bean("yinya")
    @Conditional({MacCondition.class})
    public Person person01() {
        return new Person("yinya", "28");
    }

    @Bean("zhouyangkai")
    @Conditional({WindowsCondition.class})
    public Person person02() {
        return new Person("zhouyangkai", "24");
    }


    // 工厂bean
    @Bean
    public MyFactoryBean myFactoryBean() {
        return new MyFactoryBean();
    }
}

package com.yinya.beanregistrar;

import com.yinya.bean.Person;
import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<Person> {

    // 返回一个person对象 这个对象会添加进容器
    public Person getObject() throws Exception {
        return new Person("factoryBean", "18");
    }

    public Class<?> getObjectType() {
        return Person.class;
    }

    // 是否是单例
    public boolean isSingleton() {
        return true;
    }
}

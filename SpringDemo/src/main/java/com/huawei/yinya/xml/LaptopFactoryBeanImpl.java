package com.huawei.yinya.xml;

import org.springframework.beans.factory.FactoryBean;

public class LaptopFactoryBeanImpl implements FactoryBean<Laptop> {
    public Laptop getObject() throws Exception {

        Laptop laptop = new Laptop();
        laptop.setName("factoryBeanLaptop");
        return laptop;
    }

    public Class<?> getObjectType() {
        return Laptop.class;
    }

    public boolean isSingleton() {
        return true;
    }
}

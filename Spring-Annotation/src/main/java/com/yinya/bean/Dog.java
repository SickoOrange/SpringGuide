package com.yinya.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class Dog {
    public Dog() {
        System.out.println("dog 构造器被调用");
    }


    @PostConstruct
    public void init() {
        System.out.println("Dog: @PostConstruct 被调用了");
    }

    @PreDestroy
    public void destory(){
        System.out.println("Dog: @PreDestroy 被调用了");
    }
}

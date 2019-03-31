package com.yinya.bean;

public class Car {
    public Car() {
        System.out.println("Car 创建了");
    }

    public void init(){
        System.out.println("Car 初始化");
    }

    public void destory(){
        System.out.println("Car 销毁");
    }
}

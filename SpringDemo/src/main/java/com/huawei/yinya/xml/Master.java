package com.huawei.yinya.xml;

public class Master {


    // 主人的名字
    private String name;

    public Master() {
        System.out.println("master constructor");
    }

    public String getName() {
        return name;
    }

    public Master(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Master{" +
                "name='" + name + '\'' +
                '}';
    }

    public void masterInit() {
        System.out.println("master init");
    }

    public void masterDestroy() {
        System.out.println("master destory");
    }
}

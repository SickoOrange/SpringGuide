package com.huawei.yinya;

public class Master {


    // 主人的名字
    private String name;


    public String getName() {
        return name;
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
}

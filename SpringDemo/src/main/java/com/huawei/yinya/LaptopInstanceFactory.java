package com.huawei.yinya;

public class LaptopInstanceFactory {


    public Laptop createLaotop(String name) {
        Laptop laptop = new Laptop();
        laptop.setName(name);
        laptop.setModel("2019");
        laptop.setPrice(20000);
        return laptop;
    }
}

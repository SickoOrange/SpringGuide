package com.huawei.yinya;

public class LaptopStaticFactory {

    public static Laptop createLaotop(String name) {
        Laptop laptop = new Laptop();
        laptop.setName(name);
        laptop.setModel("2019");
        laptop.setPrice(10000);
        return laptop;
    }
}

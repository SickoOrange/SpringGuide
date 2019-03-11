package com.huawei.yinya;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ComplexLaptop {

    private String name;
    private int price;
    private String model;

    // 电脑只有一个主人
    private Master master;

    // 假设一个电脑也有多个使用的人
    private List<Master> masters;

    // 随便定义一个map 数据结构
    private Map<String, Object> map;


    // properties 属性
    private Properties properties;

    public ComplexLaptop() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public List<Master> getMasters() {
        return masters;
    }

    public void setMasters(List<Master> masters) {
        this.masters = masters;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}

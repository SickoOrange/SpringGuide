package com.huawei.yinya.annotation;


import com.huawei.yinya.xml.Master;

//@Repository
public class UserDao {

    public void save(Master master) {
        System.out.println("user 已经保存到数据库：" + master.getName());
    }
}

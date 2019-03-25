package com.huawei.yinya.annotation;


import com.huawei.yinya.xml.Master;
import org.springframework.beans.factory.annotation.Autowired;

//@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void insertUser(Master master) {
        System.out.println("即将保存master到数据库");
        userDao.save(master);
    }
}

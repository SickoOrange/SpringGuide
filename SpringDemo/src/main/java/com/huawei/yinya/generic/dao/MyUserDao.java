package com.huawei.yinya.generic.dao;

import com.huawei.yinya.generic.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public class MyUserDao extends BaseDao<User> {
    public void save() {
        System.out.println("保存用户");
    }
}

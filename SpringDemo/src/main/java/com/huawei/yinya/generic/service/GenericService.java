package com.huawei.yinya.generic.service;

import com.huawei.yinya.generic.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericService<T> {

    @Autowired
    BaseDao<T> baseDao;

    public void save() {
        System.out.println("自动注入dao类型："+baseDao);
        baseDao.save();
    }

}

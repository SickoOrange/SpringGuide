package com.huawei.yinya.generic.dao;


import com.huawei.yinya.generic.bean.Book;
import org.springframework.stereotype.Repository;

@Repository
public class MyBookDao extends BaseDao<Book> {
    public void save() {
        System.out.println("保存图书");
    }
}


package com.huawei.yinya;

import org.junit.Test;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataSourceTest {

    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("datasource.xml");


    @Test
    public void test1() throws SQLException {
        DataSource comboPooledDataSource = (DataSource) ctx.getBean("comboPooledDataSource");
        System.out.println("connection: "+comboPooledDataSource.getConnection());

    }


}
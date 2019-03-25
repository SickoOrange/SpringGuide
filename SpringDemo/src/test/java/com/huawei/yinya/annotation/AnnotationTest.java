package com.huawei.yinya.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:annotation.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AnnotationTest {
    //ApplicationContext context = new ClassPathXmlApplicationContext("annotation.xml");

    @Autowired
    UserDao userDao;

    @Test
    public void test() {
      //  UserService dbService = (UserService) context.getBean("userService");
       // System.out.println(dbService);
    }


    @Test
    public void test1() {
     //   UserService userService = (UserService) context.getBean("userService");
      //  userService.insertUser(new Master("yinya"));
    }

    @Test
    public void test2() {
        System.out.println(userDao);
    }
}
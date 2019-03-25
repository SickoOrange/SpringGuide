package com.huawei.yinya.generic.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class GenericServiceTest {
    ApplicationContext ioc = new ClassPathXmlApplicationContext("generic.xml");

    @Test
    public void test(){
        BookService bookService = ioc.getBean(BookService.class);
        UserService userService = ioc.getBean(UserService.class);
        bookService.save();
        userService.save();
        System.out.println(bookService.getClass());
        System.out.println(bookService.getClass().getSuperclass());
        System.out.println(bookService.getClass().getGenericSuperclass());
    }

}
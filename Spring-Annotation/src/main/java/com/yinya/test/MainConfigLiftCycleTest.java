package com.yinya.test;

import com.yinya.config.MainConfigLifeCycle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainConfigLiftCycleTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(MainConfigLifeCycle.class);
        System.out.println("容器创建完成！");
       // 销毁容器
        ctx.close();
    }
}

package com.yinya.aop;

import com.yinya.config.MainConfigLifeCycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class AopTest {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AopConfig.class);
        System.out.println("容器创建完成！");

        PerformanceMonitor monitor = ctx.getBean(PerformanceMonitor.class);

        monitor.dataAnalyze(Arrays.asList(10, 20));
    }
}

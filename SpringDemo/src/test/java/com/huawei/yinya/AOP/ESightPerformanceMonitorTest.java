package com.huawei.yinya.AOP;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ESightPerformanceMonitorTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml");


    @Test
    public void test() {

        List<String> rawData = ImmutableList.of("10", "20", "30", "40", "50");

        PerformanceMonitor eSightPerformanceMonitor = new EsightPerformanceMonitor();

        // 获取代理对象
        PerformanceMonitor proxyHandler = EsightPerformanceMonitorProxy.getProxyHandler(eSightPerformanceMonitor);

        // 执行
        List<Integer> collectResult = proxyHandler.dataCollect(rawData);
        System.out.println("=====================");
        int analyzeResult = proxyHandler.dataAnalyze(collectResult);
        System.out.println("=====================");
        String report = proxyHandler.dataReport(analyzeResult);
        System.out.println("=====================");
    }


    @Test
    public void aopTest(){

        List<String> rawData = ImmutableList.of("10", "20", "30", "40", "50");
        // 需要用接口类型来获取，不要用实现类的类型去获取
        PerformanceMonitor aop = ctx.getBean(PerformanceMonitor.class);
        aop.dataCollect(rawData);

     //   System.out.println(aop);
       // System.out.println(aop.getClass());

        // 没有接口的情况
//        EsightPerformanceMonitorNoInterface noInterface = ctx.getBean(EsightPerformanceMonitorNoInterface.class);
//        noInterface.dataCollect(Lists.newArrayList());
//        System.out.println(noInterface);
//        System.out.println(noInterface.getClass());
    }

}
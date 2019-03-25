package com.huawei.yinya.AOP;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

public class ESightPerformanceMonitorTest {


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

}
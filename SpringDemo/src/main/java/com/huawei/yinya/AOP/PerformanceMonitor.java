package com.huawei.yinya.AOP;

import java.util.List;

/**
 * 性能监控
 */
public interface PerformanceMonitor {

    /**
     * 数据采集
     */
    public List<Integer> dataCollect(List<String> rawData);

    /**
     * 数据分析
     */
    public int dataAnalyze(List<Integer> data);

    /**
     * 数据展示
     */
    public String dataReport(int result);

}

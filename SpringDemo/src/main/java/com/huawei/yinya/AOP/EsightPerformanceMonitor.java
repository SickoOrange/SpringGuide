package com.huawei.yinya.AOP;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EsightPerformanceMonitor implements PerformanceMonitor {

//    public List<Integer> dataCollect(List<String> rawData) {
//        System.out.println("开始数据采集,元数据 -->" + rawData.toString());
//        List<Integer> data = rawData.stream().map(Integer::valueOf).collect(Collectors.toList());
//        System.out.println("数据采集完成，并完成转换 -->" + data.toString());
//        return data;
//    }
//
//    public int dataAnalyze(List<Integer> data) {
//        System.out.println("开始数据分析，-->" + data.toString());
//        Optional<Integer> sum = data.stream().reduce((i1, i2) -> i1 + i2);
//        assert sum.isPresent();
//        System.out.println("数据分析结束 结果--->" + sum.get());
//        return sum.get();
//    }
//
//    public String dataReport(int result) {
//        System.out.println("开始数据展示，reuslt int=" + result);
//        String str = "" + result;
//        System.out.println("结束数据展示，result string=" + str);
//        return str;
//    }

    public List<Integer> dataCollect(List<String> rawData) {
        return rawData.stream().map(s -> Integer.valueOf(s.substring(0, s.length() - 1))).collect(Collectors.toList());
    }

    public int dataAnalyze(List<Integer> data) {
        Optional<Integer> sum = data.stream().reduce((i1, i2) -> i1 + i2);
        assert sum.isPresent();
        return sum.get();
    }

    public String dataReport(int result) {
        return "report:" + result;
    }
}

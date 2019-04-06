package com.yinya.aop;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EsightPerformanceMonitor implements PerformanceMonitor {


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

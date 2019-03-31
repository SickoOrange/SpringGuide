package com.huawei.yinya.AOP;


import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExecutorTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 5000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10));
        executor.allowCoreThreadTimeOut(true);

        ThreadPoolExecutor executor2 = new ThreadPoolExecutor(5, 5, 5000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10));
        executor2.allowCoreThreadTimeOut(false);
    }
}

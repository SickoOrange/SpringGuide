package com.huawei.yinya.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;



public class ProxyClass {

    public static void methodStart(Method method, Object... args) {
        System.out.println("动态代理：开始执行方法:" + method.getName() + " 参数：" + Arrays.asList(args));
    }

    public static void methodReturning(Method method, Object args) {
        System.out.println("动态代理：方法正常执行结束:" + method.getName() + " 结果：" + args);

    }

    public static void methodThrowing(Method method, Exception args) {
        System.out.println("动态代理：方法执行出现异常:" + method.getName() + " Exception cause：" + args.getCause());

    }

    public static void methodFinish(Method method, Object[] args) {
        System.out.println("动态代理：方法最终执行结束" + method.getName());

    }
}

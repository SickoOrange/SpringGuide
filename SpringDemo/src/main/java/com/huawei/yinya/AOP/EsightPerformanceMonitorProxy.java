package com.huawei.yinya.AOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class EsightPerformanceMonitorProxy {


    /**
     * 为被代理对象创建其动态代理proxy对象
     *
     * @param performanceMonitor 被代理对象
     */
    public static PerformanceMonitor getProxyHandler(final PerformanceMonitor performanceMonitor) {

        // 被代理对象的 类加载器
        ClassLoader classLoader = performanceMonitor.getClass().getClassLoader();
        // 被代理对象 所实现的所有接口clazz对象
        Class<?>[] interfaces = performanceMonitor.getClass().getInterfaces();
        // 方法执行器， 执行被代理对象里面的具体的某个方法
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 注意： method.invoke(target, args);//invoke方法中的对象要从外部引入，因为如果传进来使用的话调用invoke方法会陷入死循环。
                // 利用反射执行方法 并方法的执行结果
                Object invoke = null;
                try {
                    ProxyClass.methodStart(method, args);
                    invoke = method.invoke(performanceMonitor, args);
                    ProxyClass.methodReturning(method, invoke);
                } catch (Exception e) {
                    ProxyClass.methodThrowing(method, e);
                } finally {
                    ProxyClass.methodFinish(method, args);
                }
                return invoke;
            }
        };


        Object proxy = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        // 返回代理对象
        return (PerformanceMonitor) proxy;
    }
}

package com.huawei.yinya.AOP.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AspectClass {

    //@Before("reusedPointCut()")
    public static void methodStart(JoinPoint joinPoint) {
        // 获取方法的参数
        Object[] args = joinPoint.getArgs();
        // 获取方法的名字
        String methodName = joinPoint.getSignature().getName();

        System.out.println("动态代理：开始执行方法:" + methodName + " 参数：" + Arrays.asList(args));
    }


    // 在注解上通知Spring 使用object来接受方法的返回结果
    //@AfterReturning(value = "reusedPointCut()", returning = "object")
    public static void methodReturning(JoinPoint joinPoint, Object object) {
        String name = joinPoint.getSignature().getName();
        System.out.println("动态代理：方法正常执行结束:" + name + " 结果：" + object);

    }

    //@AfterThrowing(value = "reusedPointCut()", throwing = "error")
    public static void methodThrowing(JoinPoint joinPoint, Exception error) {
        System.out.println("动态代理：方法执行出现异常:" + joinPoint.getSignature().getName() + " Exception cause：" + error.getCause());
    }

    //@After("reusedPointCut()")
    public static void methodFinish(JoinPoint joinPoint) {
        System.out.println("动态代理：方法最终执行结束: " + joinPoint.getSignature().getName());
    }

    // 可重用的切入点表达式
    @Pointcut("execution(* com.huawei.yinya.AOP..*.*(..)))")
    public void reusedPointCut() {
    }


    // 环绕通知
    // 环绕通知有一个参数 ProceedingJoinPoint
    @Around("reusedPointCut()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // 获取参数
        Object[] args = proceedingJoinPoint.getArgs();

        String methodName = proceedingJoinPoint.getSignature().getName();

        // 利用反射调用目标方法
        Object result = null;
        try {
            // 环绕前置通知
            System.out.println("环绕通知：开始执行方法:" + methodName
                    + " 参数：" + Arrays.asList(args));
            result = proceedingJoinPoint.proceed(args);
            // 环绕返回通知
            System.out.println("环绕通知：方法正常执行结束:" + methodName + " 结果：" + result);
        } catch (Exception e) {
            // 环绕异常通知
            System.out.println("环绕通知：方法执行出现异常:" + methodName + " Exception cause：" + e.getCause());

        }
        // 环绕后置通知
        System.out.println("环绕通知：方法最终执行结束: " + methodName);
        return result;
    }

}

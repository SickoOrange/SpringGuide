### AOP 环绕通知

环绕通知是最强大的通知方法。

```java
try{
	// 前置通知
  method.invoke(obj,agrs)
  // 返回通知
}catch(e){
	// 异常通知
}finally{
	// 后置通知
}
```



环绕通知例子：

```java
 		// 环绕通知 @Around
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
```



##### AOP 环绕通知的执行顺序

环绕通知的执行顺序：

前置--方法返回/方法异--后置

环绕通知与普通通知混合作用的顺序：

环绕前置，普通前置，方法内部执行，环绕返回/环绕异常， 环绕后置，普通后置，普通返回

注意：这里如果有异常的话，会被环绕异常捕获，所以普通异常通知不能捕获！



环绕通知先于普通通知执行，原因在于proceedingJoinPoint.proceed(args)，这里就是动态代理，而普通通知也需要依靠这个方法才可以！



##### 多个切面的运行顺序

- 默认情况下按照切面类的第一个字母的顺序来排序

- 或者使用@Order注解，里面的值越小优先级越高，也就越先执行

- 多个切面的执行顺序类似站栈的结构，先执行的切面，最后调用后置通知，后进入的切面最先执行后置通知


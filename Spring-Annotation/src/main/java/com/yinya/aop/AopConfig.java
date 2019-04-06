package com.yinya.aop;


import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {


    @Bean
    public EsightPerformanceMonitor eSightPerformanceMonitor() {
        return new EsightPerformanceMonitor();
    }

    @Bean
    public AspectClass aspectClass() {
        return new AspectClass();
    }
}

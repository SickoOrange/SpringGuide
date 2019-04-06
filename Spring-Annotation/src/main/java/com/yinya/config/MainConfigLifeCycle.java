package com.yinya.config;


import com.yinya.bean.Dog;
import com.yinya.lifecycle.MyApplicationContextAware;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@Import({Dog.class})
@ComponentScan("com.yinya.lifecycle")
@EnableAspectJAutoProxy
public class MainConfigLifeCycle {
    // bean的生命周期 就是bean的 创建--初始化--销毁这个过程
    // 容器管理bean的生命周期
    // 我们可以自定义初始化跟销毁方法
//    @Bean(initMethod = "init", destroyMethod = "destory")
//    public Car car() {
//        return new Car();
//    }



}

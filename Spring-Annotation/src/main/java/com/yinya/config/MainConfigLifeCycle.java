package com.yinya.config;


import com.yinya.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfigLiftCycle {
    // bean的生命周期 就是bean的 创建--初始化--销毁这个过程
    // 容器管理bean的生命周期
    // 我们可以自定义初始化跟销毁方法
    // 以前是使用

    @Bean
    public Car car() {
        return new Car();
    }

}

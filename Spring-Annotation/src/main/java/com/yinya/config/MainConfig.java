package com.yinya.config;


import com.yinya.bean.Person;
import com.yinya.beanregistrar.MyTypeFilter;
import org.springframework.context.annotation.*;


// 配置类==以前的xml配置文件的方式
// 指明当前的类是一个配置类
@Configuration
//@ComponentScan(value = "com.yinya", includeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
//}, useDefaultFilters = false)
@ComponentScans(value = {@ComponentScan(value = "com.yinya", includeFilters = {
       // @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
      //  @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
}, useDefaultFilters = false)})
public class MainConfig {

    // 给容器注册一个bean，类型为返回值的类型，id默认的是方法名
    // @Bean("customPerson") 可以为当前bean显示的指定id
    @Bean
    public Person person() {
        return new Person("yinya", "28");
    }
}

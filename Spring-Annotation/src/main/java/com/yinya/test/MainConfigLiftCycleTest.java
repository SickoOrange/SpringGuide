package com.yinya.test;

import com.yinya.bean.Dog;
import com.yinya.config.MainConfigLifeCycle;
import com.yinya.lifecycle.MyApplicationContextAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainConfigLiftCycleTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(MainConfigLifeCycle.class);
        System.out.println("容器创建完成！");
       // 销毁容器
      //  ctx.close();

        ApplicationContext context = MyApplicationContextAware.getInstance().getApplicationContext();
        System.out.println(context);
        Dog bean = context.getBean(Dog.class);
        System.out.println(bean);

    }
}

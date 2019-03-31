package com.yinya.beanregistrar;

import com.yinya.bean.ImportBean;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // importingClassMetadata 可以获取当前@Import注解的类的还有其他注解的信息

        // 这里不能返回null 因为在Spring框架内部会调取这个返回值 如果是null 就会返回空指针异常
        return new String[]{"com.yinya.bean.ImportBean"};
    }
}

package com.yinya.beanregistrar;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyTypeFilter implements TypeFilter {
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        // metadataReader 获取当前正在扫描的类的信息
        // metadataReaderFactory 获取到求他任何类的信息


        // 当前类注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        // 获取当前正在扫描的类的信息 例如 类型，实现的接口等等
        ClassMetadata classMetadata = metadataReader.getClassMetadata();

        // 获取当前类的资源信息，比如类的路径
        Resource resource = metadataReader.getResource();

        // 获取到的类名
        String className = classMetadata.getClassName();
        System.out.println("class name:----> " + className);

        if (className.contains("er")) {
            // 匹配成功 将会包含在容器中
            return true;
        }
        return false;
    }
}

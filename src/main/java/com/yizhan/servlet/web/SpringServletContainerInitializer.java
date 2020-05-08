package com.yizhan.servlet.web;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Method;
import java.util.Set;
/***
 * 1 classInfo  实现这个类的所有子类
 * 容器启动的时候调用onStartup方法，就会调用WebApplicationInitializer子类classInfo，通过反射方式执行
 */
@HandlesTypes(WebApplicationInitializer.class)
public class SpringServletContainerInitializer implements ServletContainerInitializer {
    public void onStartup(Set<Class<?>> classInfos, ServletContext ctx) throws ServletException {

       //classInfo WebApplicationInitializer子类
        for (Class<?> classInfo: classInfos){
            try {
               Method method = classInfo.getMethod("onStartup",ServletContext.class);
              Object object = classInfo.newInstance();
              method.invoke(object,ctx);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}

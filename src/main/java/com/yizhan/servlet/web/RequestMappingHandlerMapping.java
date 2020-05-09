package com.yizhan.servlet.web;

import com.yizhan.annotation.ComponentSacn;
import com.yizhan.annotation.Controller;
import com.yizhan.annotation.RequestMapping;
import com.yizhan.config.SpringMvcConfig;
import com.yizhan.handle.HandleMethod;
import com.yizhan.servlet.utils.ReflexUtil;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequestMappingHandlerMapping {
    private Map<String, HandleMethod> registry = new HashMap<>();

    //处理请求
    public void initHandlerMapping(){
        ComponentSacn componentSacn = SpringMvcConfig.class.getDeclaredAnnotation(ComponentSacn.class);
        String packages = componentSacn.value();

        if (StringUtils.isEmpty(packages)){//判断是否为空

        }

        //1.使用反射机制查找对应的Controller控制器

        Set<Class<?>> classes = ReflexUtil.getClasses(packages);

        for (Class<?> info:classes){
            Controller controller = info.getDeclaredAnnotation(Controller.class);
           if (controller == null){
               continue;
           }
           //遍历requestMapping(如果不为null)
            Method[] declaredMethods = info.getDeclaredMethods();//获取所有方法
            for (Method methodInfo: declaredMethods){
               RequestMapping requestMapping = methodInfo.getDeclaredAnnotation(RequestMapping.class);
               if (requestMapping ==null){
                   continue;
               }
               String url = requestMapping.value();//保存的是url路径，保存到集合里面List<HandlerMapping>

                registry.put(url,new HandleMethod(newInstance(info),methodInfo ));//传入一个初始化对象
            }
        }

    }

    private Object newInstance(Class classInfo){


        try {
            Object value =classInfo.newInstance();
            return  value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;

    }


    //根据url获取对应的对象
    public HandleMethod getHandlerMethod(String url){
        return registry.get(url);

    }

}

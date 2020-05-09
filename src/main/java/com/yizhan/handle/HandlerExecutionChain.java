package com.yizhan.handle;

import com.yizhan.view.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HandlerExecutionChain {
    HandleMethod handleMethod;

    public HandlerExecutionChain(HandleMethod handleMethod) {
        this.handleMethod = handleMethod;
    }


    public ModelAndView handler() throws InvocationTargetException, IllegalAccessException {
        //1.使用java反射执行请求
        Method method = handleMethod.getMethod();
        Object bean = handleMethod.getBean();
        //2/执行请求
        Object viewName = method.invoke(bean,null);
        ModelAndView modelAndView = new ModelAndView((String)viewName);
        return modelAndView;

    }


}

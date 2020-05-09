package com.yizhan.handle;

import java.lang.reflect.Method;

public class HandleMethod {

    private Object bean;  //bean对象
    private Method method; //method

    public HandleMethod(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}

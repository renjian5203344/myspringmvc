package com.yizhan.servlet;

public class DispatcherServlet extends FrameworkServlet {

    @Override
    protected void onRefresh() {
        System.out.println("容器启动调用 DispatcherServlet类onRefresh()方法");
    }
}
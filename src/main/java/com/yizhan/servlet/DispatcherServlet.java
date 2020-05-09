package com.yizhan.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends FrameworkServlet {

    @Override
    protected void onRefresh() {
        System.out.println("容器启动调用 DispatcherServlet类onRefresh()方法");
    }

    @Override
    protected void doservice(HttpServletRequest req, HttpServletResponse resp) {
        doDispatch(req,resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        //1.处理请求url
        String  requestURL = req.getRequestURI();
        //2.根据url获取对应Handler
    }


}
package com.yizhan.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class HttpServletBean extends HttpServlet {


    @Override
    public void init() throws ServletException {
        //系统初始化调用我们的initServletBean进行map组装
        initServletBean();
    }

    /***
     * url 和控制层进行绑定放入到map里面，肯定在项目启动时候map
     * 1. 扫包获取class中的方法加有　RequestMapping 放入map集合中
     * 1. key url: vlue
     *
     * 2.访问请求的时候，就是通过　url查找对应执行方法，再通过反射执行该方法。
     *
     */

    protected void initServletBean(){


    }


}

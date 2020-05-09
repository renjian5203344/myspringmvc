package com.yizhan.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrameworkServlet  extends HttpServletBean {

    @Override
    protected void initServletBean() {
        onRefresh();
    }

    protected void onRefresh(){


    }


    @Override
    protected void doservice(HttpServletRequest req, HttpServletResponse resp) {
        //不做任何操作
    }
}


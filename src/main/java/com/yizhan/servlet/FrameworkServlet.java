package com.yizhan.servlet;

import javax.servlet.ServletException;

public class FrameworkServlet  extends HttpServletBean {

    @Override
    protected void initServletBean() {
        onRefresh();
    }

    protected void onRefresh(){


    }
}


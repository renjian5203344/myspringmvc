package com.yizhan.servlet.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public interface WebApplicationInitializer {

    void onStartup(ServletContext var1) throws ServletException;
}

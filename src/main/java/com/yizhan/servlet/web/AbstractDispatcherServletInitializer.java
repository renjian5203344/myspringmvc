package com.yizhan.servlet.web;

        import com.yizhan.servlet.DispatcherServlet;

        import javax.servlet.ServletContext;
        import javax.servlet.ServletException;
        import javax.servlet.ServletRegistration;

public class AbstractDispatcherServletInitializer implements WebApplicationInitializer{


    public void onStartup(ServletContext servletContext) throws ServletException {

        //1. 开始注册我们的dispatcherServlet
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcherServlet",new DispatcherServlet());
        dispatcherServlet.addMapping("/");//拦截所有的请求


    }
}

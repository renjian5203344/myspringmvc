package com.yizhan.servlet;

import com.yizhan.handle.HandleMethod;
import com.yizhan.handle.HandlerExecutionChain;
import com.yizhan.servlet.web.RequestMappingHandlerMapping;
import com.yizhan.view.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class DispatcherServlet extends FrameworkServlet {
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    public DispatcherServlet() {
        requestMappingHandlerMapping = new RequestMappingHandlerMapping();
    }


    @Override
    protected void onRefresh() {
        initStrategies();
    }


    private void initStrategies() {
        requestMappingHandlerMapping.initHandlerMappings();
    }

    @Override
    protected void doservice(HttpServletRequest req, HttpServletResponse resp) {
        doDispatch(req, resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 1.处理请求url
            String requestURI = req.getRequestURI();
            // 2.根据url查找对应的Handler
            HandlerExecutionChain handler = getHandler(requestURI);
            if (handler == null) {
                noHandlerFound(req, resp);
                return;
            }
            // 3.使用java的反射机制执行请求方法 返回对应的modelAndView
            ModelAndView modelAndView = handler.handler();
            // 4.开始渲染视图层
            render(modelAndView, req, resp);
        } catch (Exception e) {

        }
    }

    public void render(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewName = modelAndView.getViewName();
        req.getRequestDispatcher("/WEB-INF/view/" + viewName + ".jsp").forward(req, resp);
    }

    private HandlerExecutionChain getHandler(String url) {
        HandleMethod handlerMethod = requestMappingHandlerMapping.getHandlerMethod(url);
        if (handlerMethod == null) {
            return null;
        }
        HandlerExecutionChain handlerExecutionChain = new HandlerExecutionChain(handlerMethod);
        return handlerExecutionChain;
    }

    protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new Exception("没有查找到对应的请求");
    }
}


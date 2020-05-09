package com.yizhan.view;


public class ModelAndView {
    private String viewName;

    public ModelAndView(String name){
        this.viewName = name;
    }

    public String getViewName(){
        return viewName;
    }
}
package com.yizhan.controller;

import com.yizhan.annotation.Controller;
import com.yizhan.annotation.RequestMapping;

@Controller
public class IndexController {
    //输入路径127.0.0.1:8080/index,它会自动找到对应的Controller下面的index()方法
    //其实就是注解@Controller，把url和indexController.index()做映射---key和value放在一起
    //127.0.0.1:8080/index（url）--indexController.index()


    @RequestMapping("/")
    public String test(){

        return "test";

    }
}

package com.yizhan.annotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//扫描器
public @interface ComponentSacn {
    String value() default "";

}

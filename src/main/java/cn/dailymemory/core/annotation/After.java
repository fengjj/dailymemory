package cn.dailymemory.core.annotation;

import cn.dailymemory.interceptor.JeesnsInterceptor;

import java.lang.annotation.*;

/**
 * Created by dm on 2016/11/26.
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface After {
    Class<? extends JeesnsInterceptor> value();
}

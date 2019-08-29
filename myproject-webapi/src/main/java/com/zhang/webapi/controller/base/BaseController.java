package com.zhang.webapi.controller.base;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhen
 * @ClassName BaseController
 * @date 2018/11/28 4:36 PM
 **/
public abstract class BaseController {

    private static final String USER = "USER";
    /**
     * 得到 request 对象
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

}

package com.zhang.core.exception;
/**
 * @author zhen
 * @ClassName UserController
 * @date 2018/10/26 11:25 AM
 **/
public enum ErrorEnum {

    /**
     * 错误码
     */
    ERROR(-1000,"错误"),

    SESSION_ERROR(-1001,"缓存错误"),
    SESSION_NULL(-1002,"缓存为空或缓存失效"),

    METHOD_NOT_SUPPORTED(-1003,"不支持的 Rest 请求方式"),
    ILLEGAL_STATE(-1004,"非法参数"),

    REPEAT(-1005,"操作重复"),

    LOGIN_FAIL(401,"重新登录"),
    UN_AUTH(403,"没有权限"),
    USER_INVALID_ACCOUNT(605,"用户帐号或密码不正确"),
    USER_LOCK(605,"用户帐号已锁定不可用")
    ;

    private int code;
    private String msg;


    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

package com.khpc.cn.core.entity;

/**
 * @author Vinne
 * @date 2020/1/14 21:48
 * @description 回调消息类
 **/
public class JsonResult<T> extends Result {

    private static final long serialVersionUID = 4272737567506284776L;

    /**
     * 回调状态码
     */
    private Integer code;

    /**
     *回调提示信息
     */
    private String message;

    /**
     * 泛型结果集
     */
    private  T result;

    public JsonResult(){super();};

    public JsonResult(Integer code, String message, T t) {
        this.code = code;
        this.message = message;
        this.result = t;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
}

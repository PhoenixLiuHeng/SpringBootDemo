package com.phoenix.testspringboot.pojo;


/**
 * HTTP请求返回给浏览器的最外层对象
 * @param <T>
 */
public class Result<T> {

    // 返回对象状态值
    private Integer code;

    // 返回结果的消息
    private String msg;

    // 返回的结果数据
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

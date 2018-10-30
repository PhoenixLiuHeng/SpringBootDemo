package com.phoenix.testspringboot.exception;

import com.phoenix.testspringboot.enums.ResultEnum;

/**
 * 自定义异常类，实现可以在异常中添加code的功能，继承RuntimeException，因为Spring只对继承RuntimeException的异常进行事务回滚，不对继承Exception类的异常进行回滚
 */
public class GirlException extends RuntimeException {

    private Integer code;


    public GirlException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

package com.phoenix.testspringboot.handle;

import com.phoenix.testspringboot.exception.GirlException;
import com.phoenix.testspringboot.pojo.Result;
import com.phoenix.testspringboot.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionHandle {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof GirlException){
            return ResultUtils.error(((GirlException) e).getCode(), e.getMessage());
        }else {
            log.error("【系统异常】{}",e);
            return ResultUtils.error(-1, "未知错误");
        }
    }
}

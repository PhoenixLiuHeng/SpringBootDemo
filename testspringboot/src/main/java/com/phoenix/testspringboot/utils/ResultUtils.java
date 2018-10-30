package com.phoenix.testspringboot.utils;

import com.phoenix.testspringboot.pojo.Result;

public class ResultUtils {

    // 返回成功的有返回值的结果
    public static Result succcess(Object obj){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(obj);
        return result;
    }

    // 返回成功的没有返回值的结果
    public static Result succcess(){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        return result;
    }

    // 返回失败的结果
    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}

package com.cbb.common.core.domain;

import com.cbb.common.core.enums.ResultCode;
import lombok.Data;

@Data
public class Result {
    private Object data;

    private int code;

    private String msg;

    public static Result ok(){
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static Result ok(Object data){
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setData(data);
        result.setMsg(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setCode(ResultCode.ERROR.getCode());
        result.setMsg(ResultCode.ERROR.getMessage());
        return result;
    }

    public static Result fail(ResultCode resultCode){
        Result result = new Result();
        result.setCode(resultCode.getCode());
        result.setMsg(resultCode.getMessage());
        return result;
    }

    public static Result fail(int code, String message){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }
}

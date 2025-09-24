package com.example.kobe.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 成功标志 */
    private boolean success;

    /** 状态码 */
    private int code;

    /** 消息 */
    private String msg;

    /** 数据 */
    private T data;

    //成功返回
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(200);
        result.setData(data);
        result.setMsg("操作成功");
        return result;
    }

    //成功返回
    public static <T> Result<T> success() {
        return success(null);
    }

    //失败返回
    public static <T> Result<T> error() {
        return error("操作失败");
    }

    public static <T> Result<T> error(String msg) {
        return error(500, msg);
    }

    public static <T> Result<T> error(int code, String msg) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }



}

package com.example.kobe.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    /**
     * 错误码（可自定义，如400=参数错误，404=资源不存在）
     */
    private final int code;

    /**
     * 错误消息
     */
    private final String message;

    /**
     * 构造方法：使用自定义错误码和消息
     */
    public BusinessException(int code, String message) {
        super(message); // 调用父类构造方法，便于日志打印
        this.code = code;
        this.message = message;
    }

    /**
     * 简化构造方法：使用默认错误码（如400）
     */
    public BusinessException(String message) {
        this(400, message);
    }
}

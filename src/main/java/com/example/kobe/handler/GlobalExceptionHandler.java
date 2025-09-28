package com.example.kobe.handler;

import com.example.kobe.exception.BusinessException;
import com.example.kobe.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice // 组合了@ControllerAdvice和@ResponseBody，用于处理REST接口异常
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        // 业务异常，记录完整堆栈信息
        log.error("业务异常：", e);
        return Result.error(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(DuplicateKeyException.class)
    public Result<Void> handleDuplicateKeyException(DuplicateKeyException e) {
        // 唯一索引冲突，记录完整堆栈信息
        log.error("唯一索引冲突：", e);
        return Result.error("唯一索引冲突，请勿重复添加");
    }


    /**
     * 处理数据库异常
     */
    @ExceptionHandler(SQLException.class)
    public Result<Void> handleSQLException(SQLException e) {
        // 数据库异常，记录完整堆栈信息
        log.error("数据库异常：", e);
        return Result.error("数据库异常，请稍后再试");
    }


    /**
     * 处理所有未被上述方法捕获的异常（兜底处理）
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        // 未知异常，记录完整堆栈信息
        log.error("未知异常：", e);
        return Result.error("服务器异常，请稍后再试");
    }

}

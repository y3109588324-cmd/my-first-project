package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//全局异常处理

@RestControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex) {
        ex.printStackTrace();//输入到控制台
        return  Result.errors(StringUtils.hasText(ex.getMessage()) ? ex.getMessage() : "操作失败");
    }
}

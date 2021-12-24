package com.adjaisd.common.exception.handler;

import com.adjaisd.common.exception.CommonException;
import com.adjaisd.common.exception.VerifyCodeException;
import com.adjaisd.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 全局异常
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)  // 指定对什么异常处理
    @ResponseBody  // 为了返回数据
    public Result error(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.fail().message("执行了全局异常处理");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.fail().message("执行了ArithmeticException处理");
    }

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public Result error(CommonException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMessage());
    }

    @ExceptionHandler(VerifyCodeException.class)
    @ResponseBody
    public Result error(VerifyCodeException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMessage());
    }
}

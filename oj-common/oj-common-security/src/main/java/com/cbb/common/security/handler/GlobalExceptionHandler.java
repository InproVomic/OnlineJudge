package com.cbb.common.security.handler;

import com.cbb.common.core.domain.Result;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.HttpRequestMethodNotSupportedException;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器

 */

@RestControllerAdvice

//@Slf4j

public class GlobalExceptionHandler {

    /**
     * 请求⽅式不⽀持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result
    handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
// log.error("请求地址'{}',不⽀持'{}'请求", requestURI, e.getMethod());
        return Result.error();
    }

    /**
     * 拦截运⾏时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
// log.error("请求地址'{}',发⽣异常.", requestURI, e);
        return Result.error();
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();

// log.error("请求地址'{}',发⽣异常.", requestURI, e);
        return Result.error();
    }
}

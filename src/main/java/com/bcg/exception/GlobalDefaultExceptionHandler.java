package com.bcg.exception;/**
 * Created by 11857 on 2019/9/29.
 */

import com.bcg.entity.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @ClassName GlobalDefaultExceptionHandler
 * @Description TODO
 * @Author 11857
 * @Date 20190929 19:57
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

    /**
     * NoHandlerFoundException 404 异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseResult handlerNoHandlerFoundException(NoHandlerFoundException exception) {
        //outPutErrorWarn(NoHandlerFoundException.class, CommonErrorCode.NOT_FOUND, exception);
        return new ResponseResult("500404","客户端异常");
    }

    /**
     * HttpRequestMethodNotSupportedException 405 异常处理
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult handlerHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) {
        exception.printStackTrace();
        //outPutErrorWarn(HttpRequestMethodNotSupportedException.class,CommonErrorCode.METHOD_NOT_ALLOWED, exception);
        return new ResponseResult("500405","HTTP请求异常");
    }

    /**
     * HttpMediaTypeNotSupportedException 415 异常处理
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseResult handlerHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException exception) {
        exception.printStackTrace();
        return new ResponseResult("500415","定位错误");
    }

    /**
     * Exception 类捕获 500 异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseResult handlerException(Exception e) {
        e.printStackTrace();
        return new ResponseResult("500500","服务执行异常");
    }

    /**
     * BusinessException 类捕获
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult handlerBusinessException(BusinessException e) {
        //outPutError(BusinessException.class, CommonErrorCode.BUSINESS_ERROR, e);
        return new ResponseResult(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * HttpMessageNotReadableException 参数错误异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return new ResponseResult("500600",e.getRootCause().getMessage());
    }

}
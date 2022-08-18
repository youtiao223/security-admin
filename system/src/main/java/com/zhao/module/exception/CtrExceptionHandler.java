package com.zhao.module.exception;

import com.zhao.module.system.domain.monitor.ResponseResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice
//public class CtrExceptionHandler {
//
//    @ExceptionHandler
//    @ResponseBody
//    public ResponseResult handleException(Exception e) {
//        return new ResponseResult(300, e.getMessage());
//    }
//
//
//
//}

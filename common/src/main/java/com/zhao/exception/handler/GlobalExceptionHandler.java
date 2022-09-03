package com.zhao.exception.handler;

import com.zhao.exception.BadRequestException;
import com.zhao.exception.EntityExistException;
import com.zhao.exception.EntityNotFoundException;
import com.zhao.annotation.HttpLog;
import com.zhao.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

/**
 * 全局异常拦截
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity handleException(Throwable e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        ApiError apiError = new ApiError(BAD_REQUEST.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }
    /**
     * 处理自定义异常
     * @param e
     * @return
     */
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<ApiError> badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        ApiError apiError = new ApiError(e.getStatus(),e.getMessage());
        return buildResponseEntity(apiError);
	}

    /**
     * 处理 EntityExist
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityExistException.class)
    public ResponseEntity<ApiError> entityExistException(EntityExistException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        ApiError apiError = new ApiError(BAD_REQUEST.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 处理 EntityNotFound
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiError> entityNotFoundException(EntityNotFoundException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        ApiError apiError = new ApiError(NOT_FOUND.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }



    /**
     * 权限不足
     * 处理Spring Security授权异常 AccessDeniedException
     */
    @HttpLog
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiError> accessDeniedException(AccessDeniedException e) throws AccessDeniedException {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        ApiError apiError = new ApiError(FORBIDDEN.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 认证失败
     * 处理Spring Security认证异常 AccessDeniedException
     */
    @HttpLog
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<ApiError> accessDeniedException(AuthenticationException e) throws AccessDeniedException {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        ApiError apiError = new ApiError(UNAUTHORIZED.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 统一返回
     * @param apiError
     * @return
     */

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}

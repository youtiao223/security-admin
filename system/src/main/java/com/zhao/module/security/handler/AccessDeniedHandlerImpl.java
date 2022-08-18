package com.zhao.module.security.handler;

import com.alibaba.fastjson.JSON;
import com.zhao.log.annotation.HttpLog;
import com.zhao.module.system.domain.monitor.ResponseResult;
import com.zhao.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权异常处理
 * 由于交给全局异常处理, 目前不生效
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @HttpLog
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "权限不足");

        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}

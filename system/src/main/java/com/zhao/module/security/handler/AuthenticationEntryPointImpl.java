package com.zhao.module.security.handler;

import com.alibaba.fastjson.JSON;
import com.zhao.log.annotation.HttpLog;
import com.zhao.module.system.domain.monitor.ResponseResult;
import com.zhao.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证异常处理
 * 由于交给全局异常处理，目前不生效
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @HttpLog
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "用户认证失败");

        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}

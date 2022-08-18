package com.zhao.module.security.fiter;


import com.zhao.utils.JwtUtil;
import com.zhao.module.security.domain.LoginUserInfo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    /**
     *  获取 token 并解析 token
     *  如果 token 不存在或者解析错误, 直接放行, 交给 SpringSecurity 处理
     *  token 验证通过, 用用户信息和权限信息构造可信的 Authentication, 放入上下文
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("authorization");

        // 如果请求头中没有token , 放行到下一个 filter
        // 这里不需要抛出异常,交给 Spring Security 框架处理
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        token = token.replace("Bearer", "");
        // 解析 token 获取用户登录信息, 解析错误抛出异常
        LoginUserInfo loginUserInfo;
        try {
            loginUserInfo = JwtUtil.parseJWT(token, LoginUserInfo.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // 通过认证, 生成可信的 Authentication
        // todo 获取权限信息封装到 Authentication
        Authentication auth = new UsernamePasswordAuthenticationToken(loginUserInfo, null,
                loginUserInfo.getPermissions()
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }
}

package com.zhao.module.system.service.impl;

import com.zhao.module.security.domain.LoginUserDetail;
import com.zhao.module.security.domain.LoginUserInfo;
import com.zhao.module.system.domain.monitor.ResponseResult;
import com.zhao.module.system.dto.User;
import com.zhao.utils.JwtUtil;
import com.zhao.module.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {
        // 使用 AuthenticationManager 进行用户认证
        // 认证通过返回包含用户信息的 token
        // 否则抛出异常

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        String jwt = null;
        try {
            // 验证失败, 会抛出 AuthenticationException 异常
            authentication = authenticationManager.authenticate(authentication);

            // 根据成功认证的对象创建用户的信息
            LoginUserInfo loginUserInfo = createLoginUserInfo(authentication);

            // 将用户信息存入 token
            jwt = JwtUtil.createJWT(loginUserInfo);

        } catch (AuthenticationException e) {
            throw e;
        }

        Map<String, String> map = new HashMap<>();

        map.put("token", jwt);

        return new ResponseResult(200, "登录成功", map);
    }

    @Override
    public void logout(User user) {

    }


    private LoginUserInfo createLoginUserInfo(Authentication auth) {
        LoginUserDetail loginUserDetail = (LoginUserDetail) auth.getPrincipal();
        return new LoginUserInfo(
                loginUserDetail.getUsername(),
                loginUserDetail.getUser().getNickName(),
                loginUserDetail.getUser().getPassword(),
                loginUserDetail.getPermissions());
    }


    public static void main(String[] args) throws Exception {


        LoginUserDetail loginUserDetail = new LoginUserDetail();
        loginUserDetail.setPermissions(new ArrayList<>(Arrays.asList("admin", "test")));

        User user = new User();
        user.setUserName("name");
        user.setNickName("name");
        user.setPassword("pwd");

        loginUserDetail.setUser(user);
        LoginUserInfo loginUserInfo = new LoginUserInfo(
                loginUserDetail.getUsername(),
                loginUserDetail.getUser().getNickName(),
                loginUserDetail.getUser().getPassword(),
                loginUserDetail.getPermissions());
        String jwt = JwtUtil.createJWT(loginUserInfo);
        loginUserInfo = JwtUtil.parseJWT(jwt, LoginUserInfo.class);
        System.out.println(loginUserInfo);
    }
}

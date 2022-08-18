package com.zhao.module.system.controller;

import com.zhao.module.system.domain.monitor.ResponseResult;
import com.zhao.module.system.dto.User;
import com.zhao.module.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/user/login")
    public ResponseResult<User> login(@RequestBody User user) {
        return loginService.login(user);

    }


    // todo 退出登录
    @PostMapping("/user/logout")
    public void logout(@RequestBody User user) {
        loginService.logout(user);

    }


}

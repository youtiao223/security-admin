package com.zhao.module.system.service;

import com.zhao.module.system.domain.monitor.ResponseResult;
import com.zhao.module.system.entity.User;

public interface LoginService {
    ResponseResult<User> login(User user);

    void logout(User user);
}

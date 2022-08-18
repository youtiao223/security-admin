package com.zhao;

import com.zhao.module.system.dto.User;
import com.zhao.mapper.MenuMapper;
import com.zhao.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;


    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testUserMapper() throws Exception {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testMenuMapper() throws Exception {
        List<String> perms = menuMapper.selectPermsByUserId(3L);

        System.out.println(perms);
    }
}

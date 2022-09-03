package com.zhao.module.system.service.impl;

import com.zhao.module.system.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MenuServiceImplTest {

    @Autowired
    private MenuService service;

    @Test
    public void getAllMenus() {
        service.getAllMenus();
    }


}
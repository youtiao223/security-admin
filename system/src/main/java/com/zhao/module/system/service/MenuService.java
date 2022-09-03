package com.zhao.module.system.service;

import com.zhao.module.system.entity.Menu;

import java.util.List;

public interface MenuService  {
    /**
     * 查询所有功能菜单
     *
     * @return
     */
    List<Menu> getAllMenus();
}

package com.zhao.module.system.service.impl;

import com.zhao.module.system.entity.Menu;
import com.zhao.module.system.mapper.MenuMapper;
import com.zhao.module.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhao
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper mapper;

    /**
     * 查询所有功能菜单
     *
     * @return
     */
    public List<Menu> getAllMenus() {
        List<Menu> menus = mapper.selectList(null);
        return menus;
    }

}

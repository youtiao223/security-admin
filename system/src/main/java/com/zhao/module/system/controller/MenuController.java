package com.zhao.module.system.controller;

import com.zhao.module.system.domain.monitor.ResponseResult;
import com.zhao.module.system.domain.vo.MenuListVo;
import com.zhao.module.system.domain.vo.MenuVo;
import com.zhao.module.system.entity.Menu;
import com.zhao.module.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhao
 */
@RestController
public class MenuController {

    @Autowired
    private MenuService menuMapper;

    /**
     * 列出所有权限行为信息
     */

    @GetMapping("/menu/list")
    @PreAuthorize("hasAnyAuthority('menu:list')")
    public ResponseResult<MenuListVo> listAllMenus() {
        List<Menu> menus = menuMapper.getAllMenus();
        MenuListVo menuListVo = new MenuListVo();

        List<MenuVo> menuList = new ArrayList<>();
        for (Menu menu : menus) {
            MenuVo menuVo = new MenuVo();
            menuVo.setMenuName(menu.getMenuName());
            menuVo.setPerms(menu.getPerms());
            menuList.add(menuVo);
        }
        menuListVo.setMenuList(menuList);
        ResponseResult<MenuListVo> result = new ResponseResult<>(HttpStatus.OK.value(), menuListVo);
        return result;
    }
}

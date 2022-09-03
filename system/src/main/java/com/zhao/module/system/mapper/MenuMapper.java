package com.zhao.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhao.module.system.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据用户 id 查询权限限定名
     * @param userId
     * @return
     */
    List<String> selectPermsByUserId(Long userId);
}

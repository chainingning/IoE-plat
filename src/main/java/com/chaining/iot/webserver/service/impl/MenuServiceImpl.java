package com.chaining.iot.webserver.service.impl;

import com.chaining.iot.common.constans.UserConstants;
import com.chaining.iot.common.utils.StringUtils;
import com.chaining.iot.common.utils.TreeUtils;
import com.chaining.iot.framework.web.domain.Ztree;
import com.chaining.iot.webserver.domain.Menu;
import com.chaining.iot.webserver.domain.Role;
import com.chaining.iot.webserver.mapper.MenuMapper;
import com.chaining.iot.webserver.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TODO
 *
 * @program: wxstcgateway
 * @ClassName MenuServiceImpl
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-15 21:11
 * @Version 1.0
 **/
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectMenusByUser() {
        List<Menu> menus = menuMapper.selectMenuNormalAll();
        return TreeUtils.getChildPerms(menus, 0);
    }

    @Override
    public List<Menu> selectMenuList(Menu menu) {
        return null;
    }

    @Override
    public List<Menu> selectMenuAll() {
        return null;
    }

    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Ztree> roleMenuTreeData(Role role) {
        return null;
    }

    @Override
    public List<Ztree> menuTreeData() {
        return null;
    }

    @Override
    public Map<String, String> selectPermsAll() {
        return null;
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return 0;
    }

    @Override
    public Menu selectMenuById(Long menuId) {
        return null;
    }

    @Override
    public int selectCountMenuByParentId(Long parentId) {
        return 0;
    }

    @Override
    public int selectCountRoleMenuByMenuId(Long menuId) {
        return 0;
    }

    @Override
    public int insertMenu(Menu menu) {
        return 0;
    }

    @Override
    public int updateMenu(Menu menu) {
        return 0;
    }

    @Override
    public String checkMenuNameUnique(Menu menu) {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        Menu info = menuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue())
        {
            return null;
        }
        return UserConstants.MENU_NAME_UNIQUE;
    }
}

package com.coship.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.entity.Menu;
import com.coship.entity.User;
import com.coship.mapper.MenuMapper;
import com.coship.service.MenuService;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public User loginname(String loginname) {
        return menuMapper.loginname(loginname);
    }

    @Override
    public List<Menu> queryMenuByUid(Integer userid) {
        return menuMapper.queryMenuByUid(userid);
    }

    @Override
    public List<Menu> loadAvailableMenu() {
        return menuMapper.loadAvailableMenu();
    }

    @Override
    public List<Menu> selAllMenuByUser(Integer userId) {
        return menuMapper.selAllMenuByUser(userId);
    }

    @Override
    public List<Menu> queryMenuAllList(Menu menu) {
        return menuMapper.queryMenuAllList(menu);
    }

    @Override
    public int addMenu(Menu menu) {
        return menuMapper.addMenu(menu);
    }

    @Override
    public int deleteMenu(Integer id) {
        return menuMapper.deleteMenu(id);
    }

    @Override
    public int checkMenuHasChildren(Integer pid) {
        return menuMapper.checkMenuHasChildren(pid);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }
}

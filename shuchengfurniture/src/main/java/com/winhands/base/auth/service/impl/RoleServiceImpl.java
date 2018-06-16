package com.winhands.base.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winhands.base.auth.entity.Menu;
import com.winhands.base.auth.entity.Role;
import com.winhands.base.auth.entity.RoleMenu;
import com.winhands.base.auth.entity.UserRole;
import com.winhands.base.auth.repository.RoleDao;
import com.winhands.base.auth.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao dao;

    @Override
    public List<Role> queryRoleList(Role role) throws Exception {
        return dao.queryRoleList(role);
    }

    @Override
    public int saveRole(Role role) throws Exception {
        List<Menu> menuList = role.getMenuList();
        List<RoleMenu> list = new ArrayList<RoleMenu>();
        int a = dao.saveRole(role);
        if (menuList != null && menuList.size() > 0) {
            for (Menu m : menuList) {
                RoleMenu rm = new RoleMenu();
                rm.setRoleId(role.getId());
                rm.setMenuId(m.getId());
                list.add(rm);
            }
            saveRoleMenu(list);
        }
        return a;
    }

    @Override
    public int saveRoleMenu(List<RoleMenu> list) throws Exception {
        return dao.saveRoleMenu(list);
    }

    @Override
    public int deleteRoleById(String roleId) throws Exception {
        dao.deleteUserRoleByRoleId(roleId);
        dao.deleteRoleMenuByRoleId(roleId);
        return dao.deleteRoleById(roleId);
    }

    @Override
    public int deleteRoleMenuByRoleId(String roleId) throws Exception {
        return dao.deleteRoleMenuByRoleId(roleId);
    }

    @Override
    public List<Menu> queryMenuByRoleId(String roleId) throws Exception {
        return dao.queryMenuByRoleId(roleId);
    }

    @Override
    public int updateRole(Role role, List<RoleMenu> list) throws Exception {
        dao.updateRole(role);
        dao.deleteRoleMenuByRoleId(role.getId());
        int a = 0;
        if (list != null && list.size() > 0) {
            a = dao.saveRoleMenu(list);
        }
        return a;
    }

    @Override
    public List<Role> queryRoleByUserId(String userId) throws Exception {
        return dao.queryRoleByUserId(userId);
    }

    @Override
    public int deleteUserRoleByUserId(String userId) throws Exception {
        return dao.deleteUserRoleByUserId(userId);
    }

    @Override
    public int saveUserRole(List<UserRole> list) throws Exception {
        if (list != null && list.size() > 0) {
            deleteUserRoleByUserId(list.get(0).getUserId());
        }
        return dao.saveUserRole(list);
    }

    @Override
    public int deleteUserRoleByRoleId(String roleId) throws Exception {
        return dao.deleteUserRoleByRoleId(roleId);
    }

}

package com.winhands.base.auth.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.winhands.base.auth.entity.Menu;
import com.winhands.base.auth.entity.Role;
import com.winhands.base.auth.entity.RoleMenu;
import com.winhands.base.auth.entity.UserRole;
import com.winhands.base.service.MyBatisRepository;
@Component
public interface RoleDao extends MyBatisRepository {
    List<Role> queryRoleList(Role role) throws Exception;
    int saveRole(Role role) throws Exception;
    int saveRoleMenu(List<RoleMenu> list) throws Exception;
    int deleteRoleById(String roleId) throws Exception;
    int deleteRoleMenuByRoleId(String roleId) throws Exception;
    List<Menu> queryMenuByRoleId(String roleId) throws Exception;
    int updateRole(Role role) throws Exception;
    List<Role> queryRoleByUserId(String userId) throws Exception;
    int deleteUserRoleByUserId(String userId) throws Exception;
    int saveUserRole(List<UserRole> list) throws Exception;
    int deleteUserRoleByRoleId(String roleId) throws Exception;
     
}

package com.winhands.base.auth.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.winhands.base.auth.entity.Menu;
import com.winhands.base.service.MyBatisRepository;
@Component
public interface MenuDao extends MyBatisRepository {
    List<Menu> queryMenuByUserId(String userId) throws Exception;
    List<Menu> queryMenuList() throws Exception;
    int deleteById(String id) throws Exception;
    int save(Menu menu) throws Exception;
    int update(Menu menu) throws Exception;
    List<Menu> querySonMenu(Menu m) throws Exception;
    List<Menu> queryMenu(Menu m) throws Exception;
    int deleteRoleMenu(String id) throws Exception;

}

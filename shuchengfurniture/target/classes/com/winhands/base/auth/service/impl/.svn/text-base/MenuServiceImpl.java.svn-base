package com.winhands.base.auth.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winhands.base.auth.dto.StandardDataTree;
import com.winhands.base.auth.entity.Menu;
import com.winhands.base.auth.repository.MenuDao;
import com.winhands.base.auth.service.MenuService;
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao dao;
    @Override
    public List<Menu> queryMenuByUserId(String userId) throws Exception {
        List<Menu> list = dao.queryMenuByUserId(userId);
        List<Menu> pList = new ArrayList<Menu>();
        Collections.sort(list, new Comparator<Menu>() {  //升序排列
            @Override
            public int compare(Menu o1, Menu o2) {
                if(Integer.parseInt(o1.getOrderId())>Integer.parseInt(o2.getOrderId())){
                    return 1;
                }else if(Integer.parseInt(o1.getOrderId())<Integer.parseInt(o2.getOrderId())){
                    return -1;
                }
                return 0;
            }
        });
        for(Menu m : list){
            if("0".equals(m.getParentId())){  //如果是一级菜单
                pList.add(m);
            }
        }
        for(Menu pm : pList){
            List<Menu> pmList = new ArrayList<Menu>();
            for(Menu m : list){
                if(pm.getId().equals(m.getParentId())){
                    pmList.add(m);
                }
            }
            pm.setMenuList(pmList);
        }
        return pList;
    }

    @Override
    public int deleteById(String id) throws Exception {
        dao.deleteRoleMenu(id);
        return dao.deleteById(id);
    }

    @Override
    public int save(Menu menu) throws Exception {
        return dao.save(menu);
    }

    @Override
    public int update(Menu menu) throws Exception {
        return dao.update(menu);
    }

    @Override
    public List<Menu> queryMenuList() throws Exception {
        return dao.queryMenuList();
    }

    @Override
    public List<Menu> querySonMenu(Menu m) throws Exception {
        return dao.querySonMenu(m);
    }

    @Override
    public List<Menu> queryMenu(Menu m) throws Exception {
        return dao.queryMenu(m);
    }

    @Override
    public int deleteRoleMenu(String id) throws Exception {
        return dao.deleteRoleMenu(id);
    }

    
    
    
    

}

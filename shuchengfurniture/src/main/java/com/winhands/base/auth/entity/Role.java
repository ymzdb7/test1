package com.winhands.base.auth.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Role implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2442301946400259957L;
    
    private String id;
    private String name;
    private Timestamp createTime;
    private String createUserId;
    private List<Menu> menuList;
    private String spare1;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    public String getCreateUserId() {
        return createUserId;
    }
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    public List<Menu> getMenuList() {
        return menuList;
    }
    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
    public String getSpare1() {
        return spare1;
    }
    public void setSpare1(String spare1) {
        this.spare1 = spare1;
    }
    
    

}

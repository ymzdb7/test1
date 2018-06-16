package com.winhands.base.auth.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5868702767338375501L;
    private String id;
    private String name;
    private String url;
    private String type;  //暂无意义
    private String isParent;  //1为是，2为不是
    private String parentId;
    private String orderId;
    private String isVaild;  //1为有效，2为无效
    private String jsText;
    private String createUser;
    private Timestamp createDate;
    private String spare1;  //无实际意义
    private List<Menu> menuList; // 子菜单集合

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getIsVaild() {
        return isVaild;
    }

    public void setIsVaild(String isVaild) {
        this.isVaild = isVaild;
    }

    public String getJsText() {
        return jsText;
    }

    public void setJsText(String jsText) {
        this.jsText = jsText;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public List<Menu> getMenuList() {
        if (menuList == null) {
            return new ArrayList<Menu>();
        }
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public String getSpare1() {
        return spare1;
    }

    public void setSpare1(String spare) {
        this.spare1 = spare;
    }

}

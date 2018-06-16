package com.winhands.base.auth.dto;

import java.util.List;
/**
 * ZTree标准json数据
 * @author guojun
 *
 */
public class StandardDataTree {
    private String id;
    private String name;
    private String url;
    private String isParent2;
    private boolean checked;
    private boolean open;
    private boolean nocheck;
    private List<StandardDataTree> children;
    
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
    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public boolean isOpen() {
        return open;
    }
    public void setOpen(boolean open) {
        this.open = open;
    }
    public List<StandardDataTree> getChildren() {
        return children;
    }
    public void setChildren(List<StandardDataTree> children) {
        this.children = children;
    }
    public boolean isNocheck() {
        return nocheck;
    }
    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }
    public String getIsParent2() {
        return isParent2;
    }
    public void setIsParent2(String isParent) {
        this.isParent2 = isParent;
    }
    

}

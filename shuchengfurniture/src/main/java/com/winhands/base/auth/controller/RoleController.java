package com.winhands.base.auth.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.winhands.base.auth.dto.StandardDataTree;
import com.winhands.base.auth.entity.Menu;
import com.winhands.base.auth.entity.Role;
import com.winhands.base.auth.entity.RoleMenu;
import com.winhands.base.auth.entity.UserRole;
import com.winhands.base.auth.repository.cache.RedisDao;
import com.winhands.base.auth.service.MenuService;
import com.winhands.base.auth.service.RoleService;
import com.winhands.base.user.entity.User;
import com.winhands.base.user.service.UserService;
import com.winhands.base.util.BaseJson;
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController<User>{
    @Autowired
    private RoleService impl;
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static String AC;
    /**
     * 查询角色列表并跳转到角色首页
     * @param role
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(Role role,String ac){
        logger.info("查询角色列表并跳转到角色首页");
        if(!StringUtil.isNull(ac)){
            AC = ac;
        }
        ModelAndView mv = new ModelAndView("forward:/view/role/roleIndex.jsp?ac="+AC); 
        List<Role> list = new ArrayList<Role>();
        try {
           list = impl.queryRoleList(role);
        } catch (Exception e) {
            logger.error("查询角色列表出错：",e);
        }
        mv.addObject("list",list);
        return mv;
    }
    /**
     * 获取菜单列表
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMenuList")
    public StandardDataTree getMenuList(String roleId){
        logger.info("获取菜单列表");
        List<Menu> list = new ArrayList<Menu>();
        List<StandardDataTree> tList = new ArrayList<StandardDataTree>();
        StandardDataTree root = new StandardDataTree();
        root.setName("菜单树");
        root.setId("0");
        root.setIsParent2("1");
        try {
           list = impl.queryMenuByRoleId(roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tList = list2Tree(list);
        root.setChildren(tList);
        return root;
    }
    /**
     * 根据用户查询对应菜单并将结果写入redis
     * @return
     */
    public String putMenuIntoRedis(){
        List<Menu> list = new ArrayList<Menu>();
        logger.info("根据用户查询对应菜单");
        String userId = BaseController.getUserSession().userId;
        String r = "";
        try {
            list = menuService.queryMenuByUserId(userId);
            Menu root = new Menu();
            root.setMenuList(list);
            RedisDao redisDao = new RedisDao();
            r = redisDao.putMenu(root,userId);
        } catch (Exception e) {
            r = "error";
            logger.error("根据用户查询对应菜单出错：" ,e);
        }
        return r;
    }
    /**
     * 根据用户查询对应菜单并将结果写入redis
     * @return
     */
    public void putMenuIntoSession(HttpSession session){
        List<Menu> list = new ArrayList<Menu>();
        logger.info("根据用户查询对应菜单");
        String userId = BaseController.getUserSession().userId;
        //String r = "";
        try {
            list = menuService.queryMenuByUserId(userId);
            Menu root = new Menu();
            root.setMenuList(list);
            //RedisDao redisDao = new RedisDao();
            //r = redisDao.putMenu(root,userId);
            session.setAttribute(userId,root);
        } catch (Exception e) {
            logger.error("根据用户查询对应菜单出错：" ,e);
        }
    }
    /**
     * 新增角色
     * @param role
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/addRole")
    public BaseJson addRole(Role role,String ids){
        logger.info("新增角色");
        BaseJson json = new BaseJson();
        List<Menu> list = new ArrayList<Menu>();
        if(!ids.isEmpty()){
            String[] menuIds = ids.split(",");
            for(int i = 0;i<menuIds.length;i++){
                Menu m = new Menu();
                m.setId(menuIds[i]);
                list.add(m);
            }
        }
        role.setId(StringUtil.getUUIDString());
        role.setCreateUserId(getUserSession().userId);
        role.setMenuList(list);
        try {
            impl.saveRole(role);
            json.setInfo("ok");
        } catch (Exception e) {
            json.setInfo("no");
            logger.error("保存角色出错：",e);
        }
        return json;
    }
    /**
     * 修改角色
     * @param role
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateRole")
    public BaseJson updateRole(Role role,String ids,HttpSession session){
        logger.info("修改角色");
        BaseJson json = new BaseJson();
        List<Menu> list = new ArrayList<Menu>();
        List<RoleMenu> rmList = new ArrayList<RoleMenu>();
        if(!ids.isEmpty()){
            String[] menuIds = ids.split(",");
            for(int i = 0;i<menuIds.length;i++){
                Menu m = new Menu();
                m.setId(menuIds[i]);
                list.add(m);
            }
        }
        role.setMenuList(list);
        for(Menu m : list){
            RoleMenu rm = new RoleMenu();
            rm.setRoleId(role.getId());
            rm.setMenuId(m.getId());
            rmList.add(rm);
        }
        try {
            impl.updateRole(role, rmList);
            json.setInfo("ok");
        } catch (Exception e) {
            json.setInfo("no");
            logger.error("保存角色出错：",e);
        }
        putMenuIntoSession(session);
        return json;
    }
    /**
     * 删除角色
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteRole")
    public BaseJson deleteRole(String id,HttpSession session){
        logger.info("删除角色");
        BaseJson json = new BaseJson();
        try {
            impl.deleteRoleById(id);
            json.setInfo("ok");
        } catch (Exception e) {
            json.setInfo("no");
            logger.error("删除角色出错：",e);
        }
        putMenuIntoSession(session);
        return json;
    }
    
    /**
     * 查询用户列表并跳转到用户授权页面
     * @return
     */
    @RequestMapping("/userRoleIndex")
    public ModelAndView userRoleIndex(String pageNo,String pageSize,String userName,String ac){
        if(!StringUtil.isNull(ac)){
            AC = ac;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        Page<User> page = new Page<User>();
        if(StringUtil.isNull(pageNo)){
            pageNo = "1";
        }
        if(StringUtil.isNull(pageSize)){
            pageSize = "10";
        }
        PageHelper.startPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        page = (Page<User>)userService.findUserList(map);
        System.out.println(AC);
        ModelAndView mv = new ModelAndView("forward:/view/role/userRoleIndex.jsp?ac="+AC);
        mv.addObject("list",page.getResult());
        mv.addObject("pageBt", page2PageBt(page));
        mv.addObject("userName", userName);
        return mv;
    }
    
    /**
     * 查询角色列表并标识出用户是否已授权
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserRole")
    public List<Role> getUserRole(String userId){
        logger.info("查询角色列表并标识出用户是否已授权");
        List<Role> list = new ArrayList<Role>();
        try {
            list = impl.queryRoleByUserId(userId);
        } catch (Exception e) {
            logger.error("查询用户角色列表出错：",e);
        }
        return list;
    }
    /**
     * 修改用户权限
     * @param ids
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUserRole")
    public BaseJson updateUserRole(String ids,String userId,HttpSession session){
        logger.info("修改用户权限");
        BaseJson json = new BaseJson();
        List<UserRole> list = new ArrayList<UserRole>();
        if(!ids.isEmpty()){
            String[] roleIds = ids.split(",");
            for(int i = 0;i<roleIds.length;i++){
                UserRole ur = new UserRole();
                ur.setId(StringUtil.getUUIDString());
                ur.setUserId(userId);
                ur.setRoleId(roleIds[i]);
                list.add(ur);
            }
        }
        try {
            impl.saveUserRole(list);
            json.setInfo("ok");
        } catch (Exception e) {
            logger.error("修改用户权限出错：",e);
        }
        putMenuIntoSession(session);
        return json;
    }
    
    /**
     * 菜单集合转tree格式json
     * @param list
     * @return
     */
    public List<StandardDataTree> list2Tree(List<Menu> list){
        List<StandardDataTree> tList = new ArrayList<StandardDataTree>();
        List<Menu> pList = new ArrayList<Menu>();
        boolean flag = false;
        for(Menu m : list){
            if("0".equals(m.getParentId())){  //如果是一级菜单
                pList.add(m);
                flag = true;
            }
        }
        if(!flag){
            for(Menu m : list){
                StandardDataTree t = new StandardDataTree();
                t.setId(m.getId());
                t.setName(m.getName());
                t.setIsParent2(m.getIsParent());
                if(!StringUtil.isNull(m.getSpare1())){
                    t.setChecked(true);
                }
                tList.add(t);
            }
            return tList;
        }
        for(Menu pm : pList){
           List<Menu> l = new ArrayList<Menu>();
            for(Menu m : list){
                if(pm.getId().equals(m.getParentId())){
                    l.add(m);
                }
            }
            pm.setMenuList(l);
        }
        for(Menu m : pList){
            StandardDataTree t = new StandardDataTree();
            t.setId(m.getId());
            t.setName(m.getName());
            t.setIsParent2(m.getIsParent());
            if(m.getMenuList().size()>0){
                t.setChildren(list2Tree(m.getMenuList()));
            }
            if(!StringUtil.isNull(m.getSpare1())){  //设置选中
                t.setChecked(true);
            }
            tList.add(t);
        }
        return tList;
    }

    

}

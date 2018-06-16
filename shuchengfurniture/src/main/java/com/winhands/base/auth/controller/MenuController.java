package com.winhands.base.auth.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.winhands.base.auth.dto.StandardDataTree;
import com.winhands.base.auth.entity.Menu;
import com.winhands.base.auth.repository.cache.RedisDao;
import com.winhands.base.auth.service.MenuService;
import com.winhands.base.util.BaseJson;
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController<Menu> {
    @Autowired
    private MenuService impl;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static String AC;
    /**
     * 获取对应菜单列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMenuByUser")
    public List<Menu> getMenuByUser() {
        List<Menu> list = new ArrayList<Menu>();
        logger.info("根据用户查询对应菜单");
        try {
            list = impl.queryMenuByUserId(BaseController.getUserSession().userId);
        } catch (Exception e) {
            logger.error("根据用户查询对应菜单出错：" ,e);
        }
        return list;
    }
    /**
     * 根据用户查询对应菜单并将结果写入redis
     * @return
     */
    @ResponseBody
    @RequestMapping("/putMenuIntoRedis")
    public String putMenuIntoRedis(){
        List<Menu> list = new ArrayList<Menu>();
        logger.info("根据用户查询对应菜单");
        String userId = BaseController.getUserSession().userId;
        String r = "";
        try {
            list = impl.queryMenuByUserId(userId);
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
     * 菜单管理首页
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(String ac){
        ModelAndView mv = new ModelAndView("forward:/view/menu/menuIndex.jsp");
        if(!StringUtil.isNull(ac)){
            AC = ac;
        }
        List<Menu> list = new ArrayList<Menu>();
        List<StandardDataTree> tList = new ArrayList<StandardDataTree>();
        StandardDataTree root = new StandardDataTree();
        root.setChecked(true);
        root.setName("菜单树");
        root.setId("0");
        root.setIsParent2("1");
        logger.info("查询所有菜单");
        try {
            list = impl.queryMenuList();
            tList = list2Tree(list);
            Iterator<Menu> i = list.iterator();
            while(i.hasNext()){
                String id = i.next().getParentId();
                if(!"0".equals(id)){
                    i.remove();
                }
            }
        } catch (Exception e) {
            logger.error("查询所有菜单出错：" ,e);
        }
        root.setChildren(tList);
        mv.addObject("treeJson", JSON.toJSONString(root));
        mv.addObject("list", list);
        return mv;
    }
    /**
     * 获取菜单树
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMenuTree")
    public List<StandardDataTree> getMenuTree(){
        List<StandardDataTree> tList = new ArrayList<StandardDataTree>();
        logger.info("查询所有菜单");
        try {
            tList = list2Tree(impl.queryMenuList());
        } catch (Exception e) {
            logger.error("查询所有菜单出错：" ,e);
        }
        return tList;
    }
    /**
     * 查询子菜单
     * @param m
     * @return
     */
    @ResponseBody
    @RequestMapping("/getSonMenu")
    public List<Menu> getSonMenu(Menu m){
        logger.info("查询子菜单");
        List<Menu> list = new ArrayList<Menu>();
        try {
            list = impl.querySonMenu(m);
        } catch (Exception e) {
            logger.error("查询子菜单出错：",e);
        }
        return list;
    }
    /**
     * 查询菜单详情
     * @param m
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMenuDetail")
    public Menu getMenuDetail(Menu m){
        logger.info("查询菜单详情");
        List<Menu> list = new ArrayList<Menu>();
        try {
            list = impl.queryMenu(m);
            m = list.get(0);
        } catch (Exception e) {
            logger.error("查询子菜单出错：",e);
        }
        return m;
    }
    
    /**
     * 修改菜单
     * @param m
     */
    @RequestMapping("/editMenu")
    public ModelAndView editMenu(Menu m,HttpSession session){
        logger.info("修改菜单");
        ModelAndView mv = new ModelAndView("redirect:/menu/index?ac="+AC);
        m.setType("1");  //暂无意义
        m.setCreateUser(getUserSession().userId);
        try {
            impl.update(m);
        } catch (Exception e) {
            logger.error("修改菜单出错：",e);
        }
        putMenu2Session(session);
        return mv;
    }

    /**
     * 获取最新菜单并存到session中
     * @param session
     */
    public void putMenu2Session(HttpSession session){
        List<Menu> list = new ArrayList<Menu>();
        logger.info("根据用户查询对应菜单");
        String userId = BaseController.getUserSession().userId;
        try {
            list = impl.queryMenuByUserId(userId);
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
     * 删除菜单及其子菜单
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteMenu")
    public BaseJson deleteMenu(String id, HttpSession session){
        logger.info("删除菜单及其子菜单");
        BaseJson json = new BaseJson();
        try {
            json.setInfo(impl.deleteById(id)+"");
        } catch (Exception e) {
            logger.error("删除菜单出错：",e);
        }
        putMenu2Session(session);
        return json;
    }
    
    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @RequestMapping("/addMenu")
    public ModelAndView addMenu(Menu menu,HttpSession session){
        logger.info("新增菜单");
        ModelAndView mv = new ModelAndView("redirect:/menu/index?ac="+AC);
        menu.setType("1");  //暂无意义
        menu.setCreateUser(getUserSession().userId);
        menu.setId(StringUtil.getUUIDString());
        try {
            impl.save(menu);
        } catch (Exception e) {
            logger.error("保存菜单出错：",e);
        }
        putMenu2Session(session);
        return mv;
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
//                if(("2").equals(m.getIsParent())){
//                    t.setNocheck(true);
//                }
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
//            if(("2").equals(m.getIsParent())){
//                t.setNocheck(true);
//            }
            tList.add(t);
        }
        return tList;
    }

}

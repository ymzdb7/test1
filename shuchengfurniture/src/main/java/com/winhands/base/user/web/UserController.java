package com.winhands.base.user.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.winhands.base.dict.service.DictService;
import com.winhands.base.org.entity.Org;
import com.winhands.base.org.service.OrgService;
import com.winhands.base.shiro.BaseRealm;
import com.winhands.base.user.entity.User;
import com.winhands.base.user.service.UserService;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.BaseJson;
import com.winhands.base.util.DateUtil;
import com.winhands.base.util.Digests;
import com.winhands.base.util.Encodes;
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;
import com.winhands.base.web.entity.Tree;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private DictService dictService;
    public static final int HASH_INTERATIONS = 1024;

    @RequestMapping("/index")
    public ModelAndView index(String pageNo, String pageSize, String searchName, String searchPhone) {
        logger.info("进入用户管理首页pageNo:{},pageSize:{}", pageNo, pageSize);
        int pageSizeI = BaseConstant.pageSize;
        int pageNoI = 1;
        try {
            pageNoI = Integer.parseInt(pageNo);
            pageSizeI = Integer.parseInt(pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        Map<String, Object> userMap = new HashMap<String, Object>();
        if (!StringUtil.isNull(searchName)) {
            userMap.put("userName", searchName);
        }

        if (!StringUtil.isNull(searchPhone)) {
            userMap.put("userPhone", searchPhone);
        }

        PageHelper.startPage(pageNoI, pageSizeI);
        List<User> userList = userService.findUserList(userMap);
        com.github.pagehelper.Page<User> pageBt = (com.github.pagehelper.Page) userList;
        ModelAndView mv = new ModelAndView("forward:/view/user/userlist.jsp");// redirect模式
        mv.addObject("pageList", pageBt.getResult());
        mv.addObject("pageBt", page2PageBt(pageBt));
        mv.addObject("searchName",searchName);
        mv.addObject("searchPhone",searchPhone);
        return mv;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        List<Org> list = orgService.findAll();
        // 转换成json
        ModelAndView mv = new ModelAndView("forward:/view/user/addUser.jsp");// redirect模式
        return mv;
    }

    @RequestMapping("/save")
    public ModelAndView save(User user,String password1) {
        logger.info("进入用户管理保存{}");
        user.setUserId(StringUtil.getUUIDString());
        user.setUserOpTime(DateUtil.getCurrentDate());
        user.setPasswordUpdate(DateUtil.getCurrentDate());
        user.setPassword(password1);
        entryptPassword(user);
        userService.save(user);
        return index("1", "10", "", "");
    }

    @ResponseBody
    @RequestMapping("/remove")
    public BaseJson remove(String id, String status) {
        if (StringUtil.isNull(id)) {
            return new BaseJson(600, "参数无效", "");
        } else {
            userService.deleteByUserId(id);
            return new BaseJson(1, "删除成功!", "");
        }

    }

    @RequestMapping("/edit")
    public ModelAndView edit(String pageNo, String pageSize, String userId, String searchId, String searchName, String searchPhone) {
        User user = userService.findUserByUserId(userId);
        ModelAndView mv = new ModelAndView("forward:/view/user/editUser.jsp");// redirect模式
        mv.addObject("user", user);
        mv.addObject("pageNo", pageNo);
        mv.addObject("pageSize", pageSize);
        mv.addObject("searchId", searchId);
        mv.addObject("searchName", searchName);
        mv.addObject("searchPhone", searchPhone);
        return mv;
    }

    @RequestMapping("/update")
    public ModelAndView update(User user, String pageNo, String pageSize, String id, String searchId, String searchName, String searchPhone) {
        if (StringUtil.isNull(user.getPassword())) {
            User u = userService.findUserByUserId(user.getUserId());
            user.setSalt(u.getSalt());
            user.setPassword(u.getPassword());
        } else {
            entryptPassword(user);
        }
        userService.save(user);
        return index(pageNo, pageSize,  searchName, searchPhone);
    }

    @ResponseBody
    @RequestMapping("/msUpdatePass")
    public BaseJson msUpdatePass(String userId, String userPhone, String oldpassword, String newpassword) {
        // 验证原密码是否正确
        User userTemp = userService.findUserByUserId(userId);
        User user2 = new User();
        user2.getUserId();
        user2.setPassword(oldpassword);
        byte[] salt = Encodes.decodeHex(userTemp.getSalt());
        user2.setSalt(Encodes.encodeHex(salt));
        byte[] hashPassword = Digests.sha1(oldpassword.getBytes(), salt, HASH_INTERATIONS);
        String userPass = Encodes.encodeHex(hashPassword);
        if (userPass.equals(userTemp.getPassword())) {
            // 密码正确
            User user3 = new User();
            user3.setUserId(userId);
            user3.setUserPhone(userPhone);
            user3.setPassword(newpassword);
            byte[] salt2 = Encodes.decodeHex(userTemp.getSalt());

            user3.setSalt(Encodes.encodeHex(salt2));

            byte[] hashPassword2 = Digests.sha1(user3.getPassword().getBytes(), salt2, HASH_INTERATIONS);
            newpassword = Encodes.encodeHex(hashPassword2);
            userTemp.setPassword(newpassword);
            userTemp.setUserPhone(userPhone);
            userService.save(userTemp);
            return new BaseJson(1, "修改正确", "", userTemp);
        } else {
            return new BaseJson(600, "原密码输入错误", "");
        }

    }

    private String list2TreeJson(List<Org> orgList, String searchId, String orgId, boolean urlUse, boolean selfChecked) {
        List<Tree> treeList = new ArrayList<Tree>();
        for (Org org : orgList) {
            Tree tree = new Tree();
            tree.setId(org.getOrgId());
            tree.setName(org.getOrgName());
            tree.setpId(org.getParentOrgId());
            if (urlUse) {
                tree.setUrl("../user/index?ac=200021&searchId=" + org.getOrgId());
            }
            if (!selfChecked) {
                if (org.getOrgId().equals(orgId)) {
                    tree.setChkDisabled(selfChecked);
                }
            }
            tree.setTarget("_self");
            tree.setClick("");
            tree.setChecked(org.getOrgId().equals(searchId) ? true : false);
            tree.setOpen(org.getOrgId().equals(searchId) ? true : false);
            treeList.add(tree);
        }
        // 转换成json
        return JSON.toJSONString(treeList).replace("\"checked\"", "checked").replace("\"open\"", "open");
    }

    @RequestMapping("/toChangePassword")
    public ModelAndView toChangePassword() {
        ModelAndView mv = new ModelAndView("forward:/view/user/changePass.jsp");
        return mv;
    }

    /**
     * 重置密码
     */
    @ResponseBody
    @RequestMapping("/resetPassword")
    public BaseJson resetPassword(String user_id) {
        logger.info("重置密码");
        BaseJson json = new BaseJson();
        User u = userService.findUserByUserId(user_id);
        u.setPassword("123456");
        entryptPassword(u);
        userService.save(u);
        json.setStatus(200);
        json.setMessage("密码重置成功!");
        return json;
    }

    /**
     * 修改密码
     * 
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/changePassword")
    public BaseJson changePassword(HttpServletRequest request, HttpServletResponse response) {
        logger.info("修改密码");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        BaseJson json = new BaseJson();
        try {
            User u = userService.findUserByUserId(getUserSession().userId);
            byte[] salt = Encodes.decodeHex(u.getSalt());
            byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
            if (Encodes.encodeHex(hashPassword).equals(u.getPassword())) { // 对比密码
                u.setPassword(newPassword);
                entryptPassword(u);
                userService.save(u);
                json.setStatus(200);
                json.setMessage("修改成功!");
            } else {
                json.setStatus(500);
                json.setMessage("原密码不对!");
            }
        } catch (Exception e) {
            logger.error("修改密码失败", e);
            json.setStatus(500);
        }
        return json;
    }

    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(BaseConstant.SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));
        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, BaseRealm.HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }
}

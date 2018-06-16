package com.winhands.base.user.service;

import java.util.List;
import java.util.Map;

import com.winhands.base.user.entity.User;

public interface UserService {
   /**
    * 用户登录
    * @param username
    * @return
    */
   public User findUserByLoginName(String username);
   /**
    * 获得用户列表 
    * @param conditions 
    * @return
    */
   public List<User> findUserList(Map<String, Object> conditions);
   /**
    * 获取厨师列表
    * @param
    * @return
    */
   public List<User> queryChefList(Map<String, Object> conditions);
   public User save(User user);
   
   public void deleteByUserId(String userId);
   
   public User findUserByUserId(String userId);
   //根据用户地区id 返回信息员信息
   public User findUserByOrgId(Map<String, Object> conditions);
    int updateLastLoginByUserId(String userId) throws Exception;
   
}

package com.winhands.base.user.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.winhands.base.service.MyBatisRepository; 
import com.winhands.base.user.entity.User;
@Component
public interface MBUserDao extends MyBatisRepository{
   public List<User> queryUserList(Map<String,Object> conditions);
   public List<User> queryChefList(Map<String,Object> conditions);
   public List<User> findUserByOrgId(Map<String,Object> conditions);
   int updateLastLoginByUserId(String userId) throws Exception;
}

/*   
 * Copyright (c) 2014 南京唯恒思 www.winhands.com. All Rights Reserved.   
 */     
package com.winhands.ncjc.push.service;    

import java.util.List;
import java.util.Map;

import com.winhands.ncjc.push.entity.PushMsg;
import com.winhands.ncjc.push.entity.PushUserDevice;


public interface PushApiService {
   /**
    *    findAllUser 获得联系人列表
    */
   public List<PushUserDevice> findAllUser(PushUserDevice pushUserDevice) throws Exception;
   
   /**
    *    deleteUser2Push 解除用户和设备推送绑定关系
    */ 
   public boolean  deleteUser2Push(PushUserDevice pushUserDevice) throws Exception;
   public PushUserDevice findByPushUserDevice(Map<String,Object> map);
   public List<Object> findPushUserByUserID(PushMsg pushMsg) throws Exception;
   /**
    *    saveUser2Push 注册用户和百度推送的绑定关系
    */
   public PushUserDevice  saveUser2Push(PushUserDevice pushUserDevice) throws Exception;
   public PushMsg  savePushMsg(PushMsg pushMsg) throws Exception;
   public List<PushMsg> queryUserMsg(Map<String,Object> map) throws Exception;
}
 
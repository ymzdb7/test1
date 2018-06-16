package com.winhands.ncjc.push.repository;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.winhands.base.service.MyBatisRepository;
import com.winhands.ncjc.push.entity.PushMsg;
import com.winhands.ncjc.push.entity.PushUserDevice;

/**
 * 日志数据库操作层 基于mybais
 * @author yuanl
 *
 */  
@Component
public interface MBPushDao extends MyBatisRepository{
	   public PushUserDevice findByPushUserDevice(Map<String,Object> map);
	   public List<PushMsg> queryUserMsg(Map<String,Object> map) throws Exception;
}

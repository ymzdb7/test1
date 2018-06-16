/*   
 * Copyright (c) 2014 南京唯恒思 www.winhands.com. All Rights Reserved.   
 */     
package com.winhands.ncjc.push;    

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winhands.ncjc.push.entity.PushMsg;
import com.winhands.ncjc.push.entity.PushUserDevice;

/**
 * 
 * [工程名]
 *     ydwq
 * [类名]
 *     PushThread
 * [描述]
       [多线程推送消息]
 * [History]
 *     Version  Date      Author     Content
 *     -------- --------- ---------- ------------------------
 *     1.0.0    Apr 10, 2014   lei    最初版本
 */
public class PushThread implements Runnable {
	private final Logger logger = LoggerFactory.getLogger(PushThread.class); 
    private List<Object> list;
    private Map<String,String> resultMap; 
    private PushMsg pushMsg;
    private final CountDownLatch doneSignal;

    public Map<String, String> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}
	PushThread(List<Object> userList,CountDownLatch doneSignal,PushMsg pushMsg){
		this.list = userList;
    	this.resultMap = new HashMap<String, String>();
    	this.doneSignal = doneSignal; 
    	this.pushMsg = pushMsg;
    }
	@Override
	public void run() {
		while(list.size()>0){
			PushUserDevice pushUserDevice = (PushUserDevice)list.remove(0);
			try {
				String result = PushApi.push2UserMsg(pushUserDevice, pushMsg);
				if(result.equals(PushApi.errorCode_server_failed)){
					resultMap.put(pushUserDevice.getPushUserDeviceBean().getUser_id(), "0");
				}else{
					resultMap.put(pushUserDevice.getPushUserDeviceBean().getUser_id(), result);
				} 
			} catch (Exception e) {
				//e.printStackTrace();
    			logger.error("用户推送失败{}", e.getMessage());
				logger.error("用户推送失败{}", pushUserDevice.getPushUserDeviceBean().getUser_id());
			}
			
			doneSignal.countDown();
		}
	}
	
}
 
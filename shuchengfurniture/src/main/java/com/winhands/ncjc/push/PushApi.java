/*   
 * Copyright (c) 2014 南京唯恒思 www.winhands.com. All Rights Reserved.   
 */     
package com.winhands.ncjc.push;   

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.winhands.base.util.StringUtil;
import com.winhands.base.util.json.JsonUtil;
import com.winhands.ncjc.push.entity.PushMsg;
import com.winhands.ncjc.push.entity.PushUserDevice;

/**
 * 
 * [工程名]
 *     ydwq
 * [类名]
 *     PushApi
 * [描述]
       [百度推送工具方法]
 * [History]
 *     Version  Date      Author     Content
 *     -------- --------- ---------- ------------------------
 *     1.0.0    Apr 9, 2014   lei    最初版本
 */
public  class   PushApi {
	private static final Logger logger = LoggerFactory.getLogger(PushApi.class);
    // private static final  String apiKey = "AYQVP6GKe7sUs9vsvpd41gbB";//应用key
    //private static final  String secretKey = "Ep7g04pVdGY5z9NvoUKMEKVZ8I24exE0";//
    //private static final  String apiKey = "2052139";//应用key
	public static final  String apiKey = "1pRrkACmDdTjqidT1tGy3XyP";//PropertiesUtil.getProperties().getProperty("apiKey");
    public static final  String secretKey = "4S4wbNUquu4jTjTmk27vkAsnOILV6ga8";//= PropertiesUtil.getProperties().getProperty("secretKey");// 
	
    public static final  String dgbAndroidApiId = "10165756";//应用key
    public  static final  String dgbAndroidApiKey = "EEjDwnDyEnImiy8PpZOCIfFN";
    public static final  String dgbAndroidSecretKey = "qPmNRDn2oOmiHhsELp5UGPrXkrGxF1Wd";// 
    
    public static final  String dgbIosApiId = "8764313";//ios应用key
    public static final  String dgbIosApiKey = "EEjDwnDyEnImiy8PpZOCIfFN";
    public static final  String dgbIosSecretKey = "qPmNRDn2oOmiHhsELp5UGPrXkrGxF1Wd";// 
    
    
	public static final  String cmd_location ="cmd_location";//定位
	public static final  String cmd_notify ="cmd_notify";//通知
	public static final  String cmd_chat ="cmd_chat"; //聊天
	
	public static final  String msg_type_text = "1"; //文本
	public static final  String msg_type_pic= "2"; //照片
	public static final  String msg_type_voice = "3"; //声音
	public static final  String msg_type_file= "4"; //文件
	
	public static final  String errorCode_param_inValid = "601";//参数无效 
	public static final  String errorCode_client_exception = "602";//客户端端异常 
	public static final  String errorCode_server_exception = "603";//服务端异常 
	public static final  String errorCode_server_success = "200";//推送成功
	public static final  String errorCode_server_failed = "600";//推送失败
	
	private static final  int  maxRetryTimes = 3;//最大重试次数
	/**
	 * 
	 * <b>Summary: </b>
	 *    push2UserMsg push2UserMsg 推送单播消息(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
	 * String
	 * @param pushUserDevice
	 * @param pushMsg
	 * @return 
	 * push2UserMsg
	 * Apr 10, 2014
	 */
    public static String push2UserMsg(PushUserDevice pushUserDevice,PushMsg pushMsg){
    	if(pushMsg.getCmd() == null||pushUserDevice==null){
	    	logger.error("pushUserDevice:{},推送单播消息不正确：pushMsg.cmd：{}",pushUserDevice,pushMsg.getCmd());
	    	return errorCode_param_inValid;//参数无效
	    }
	    // 2.创建BaiduChannelClient对象实例 
		
		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

 		// 2. build a BaidupushClient object to access released interfaces
 		BaiduPushClient pushClient = new BaiduPushClient(pair,BaiduPushConstants.CHANNEL_REST_URL);
 		
		// 3. 若要了解交互细节，请注册YunLogHandler类 
 		pushClient.setChannelLogHandler(new YunLogHandler() {
 			@Override
 			public void onHandle(YunLogEvent event) {
 				logger.error("注册YunLogHandler类：{}",event.getMessage());
 			}
 		});
//        try { 
//			// 4. 创建请求类对象
//			// 手机端的ChannelId， 手机端的UserId， 先用1111111111111代替，用户需替换为自己的
//			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
//			// device_type => 1: web 2: pc 3:android 4:ios 5:wp
//			request.setDeviceType(pushUserDevice.getDevice_type()==null?3:Integer.valueOf(pushUserDevice.getDevice_type()));
//			request.setChannelId(Long.valueOf(pushUserDevice.getChannel_id()));
//			request.setUserId(pushUserDevice.getBd_user_id());
//			String msg = transformPushMsg(pushMsg);
//			request.setMessage(msg);
//            // 5. 调用pushMessage接口 重试三次
//			int  _maxRetryTimes=0;
//			while(_maxRetryTimes<=maxRetryTimes){
//				_maxRetryTimes++;
//				// 6. 认证推送成功
//				PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
//				if(response.getSuccessAmount()==1){
//					logger.info("user_id:{}, time:{} ,push amount :{}",pushUserDevice.getUser_id(),System.currentTimeMillis(),response.getSuccessAmount());
//					return errorCode_server_success;
//				}else{
//					logger.error("user_id:{}, time:{} ,push amount :{}",pushUserDevice.getUser_id(),System.currentTimeMillis(),response.getSuccessAmount());
//				} 
//			}
//			//重试三次依旧失败，则返回失败
			return errorCode_server_failed;
//		} catch (ChannelClientException e) {
//			// 处理客户端错误异常
//        	e.printStackTrace();
//			logger.error("处理客户端错误异常:{}", e.getMessage());
//			return errorCode_server_exception;
//		} catch (ChannelServerException e) {
//			// 处理服务端错误异常
//			e.printStackTrace();
//			logger.error("处理服务端错误异常：request_id: {}, error_code: {}, error_message: {}",e.getRequestId(), e.getErrorCode(), e.getErrorMsg());
//			return errorCode_server_exception;
//		}
    }
    
    /**
     * 
     * <b>Summary: </b>
     *    push2Msg 推送广播消息(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
     * String
     * @param push2Msg
     * @return 
     * push2Notice
     * Apr 9, 2014
     */
    public static String push2Msg(PushMsg pushMsg){
//    	if(pushMsg.getCmd() == null){
//	    	logger.error("userid:{},推送广播消息不正确：pushMsg.cmd：{}",pushMsg.getCmd());
//	    	return errorCode_param_inValid;//参数无效
//	    }
//	    // 2.创建BaiduChannelClient对象实例
//		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
//		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
//		
//		// 3. 若要了解交互细节，请注册YunLogHandler类
//		channelClient.setChannelLogHandler(new YunLogHandler() {
//			@Override
//			public void onHandle(YunLogEvent event) { 
//				logger.error("注册YunLogHandler类：{}",event.getMessage());
//			}
//		});
//        try { 
//			// 4. 创建请求类对象 
//        	PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
//			request.setDeviceType(3); // device_type => 1: web 2: pc 3:android 4:ios 5:wp
//			String msg = transformPushMsg(pushMsg);
//			request.setMessage(msg); 
//            // 5. 调用pushMessage接口
//			int  _maxRetryTimes=0;
//			while(_maxRetryTimes<=maxRetryTimes){
//				_maxRetryTimes++;
//				// 6. 认证推送成功
//				PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);
//				if(response.getSuccessAmount()>=1){
//					logger.info("time:{} ,push amount :{}",System.currentTimeMillis(),response.getSuccessAmount());
//					return errorCode_server_success;
//				}else{
//					logger.error("time:{} ,push amount :{}",System.currentTimeMillis(),response.getSuccessAmount());
//					
//				} 
//			}
//		    //重试三次 失败
//			return errorCode_server_failed;
//        } catch (ChannelClientException e) {
//			// 处理客户端错误异常
//        	e.printStackTrace();
//			logger.error("推送广播消息处理客户端错误异常:{}", e.getMessage());
//			return errorCode_client_exception;
//		} catch (ChannelServerException e) {
//			// 处理服务端错误异常
//			e.printStackTrace();
//			logger.error("推送广播消息处理服务端错误异常：request_id: {}, error_code: {}, error_message: {}",e.getRequestId(), e.getErrorCode(), e.getErrorMsg());
			return errorCode_server_exception;
//		}
    }
    
    /**
     * 
     * <b>Summary: </b>
     *    push2UserGroup  向用户组推送消息
     * String
     * @param user_ids
     * @param channel_ids
     * @param pushMsg
     * @return 
     * push2UserGroup
     * Apr 10, 2014
     */
    public static  String  push2UserGroup(List<Object> pushUserDeviceList,PushMsg pushMsg){
    	long startTime =System.currentTimeMillis();
        CountDownLatch doneSignal = new CountDownLatch(pushUserDeviceList.size());//同步辅助类
		PushThread pushThreadnew = new PushThread(pushUserDeviceList,doneSignal,pushMsg);
		//确定一个合理的线程个数 最多4个线程
		int threads = 3;
		if(pushUserDeviceList.size()>=12){
			threads = 4;
		}else if(pushUserDeviceList.size()<12&&pushUserDeviceList.size()>=3){
			threads = 3;
		}else{
			threads = pushUserDeviceList.size();
		}
		
		try {
			for (int i = 0; i < threads; ++i) {// create and start threads
			    new Thread(pushThreadnew).start();
			}  
		    doneSignal.await();
		} catch (Exception e) {
			logger.error("多线程推送消息时异常");
	    	e.printStackTrace();
			return errorCode_server_exception;//参数无效
        }  
		System.out.println("\r<br>执行耗时 : "+(System.currentTimeMillis()-startTime)/1000f+" 秒 ");
		String jsonString = JsonUtil.ObjectToJson(pushThreadnew.getResultMap()); 
		return jsonString;
    }
    
    /**
     * 
     * <b>Summary: </b>
     *    push2UserNotific  向用户推送通知消息 
     * String
     * @param user_id
     * @param channel_id
     * @param pushMsg
     * @return 
     * push2UserNotific
     * Apr 9, 2014
     */
    public static String push2UserNotific(PushUserDevice pushUserDevice,String tiltle,String description){
//    	if(pushUserDevice == null){
//	    	logger.error("pushUserDevice:{},推送通知消息不正确",pushUserDevice);
//	    	return errorCode_param_inValid;//参数无效
//	    }
//	    // 2.创建BaiduChannelClient对象实例
//		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
//		
//		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
//		// 3. 若要了解交互细节，请注册YunLogHandler类
//		channelClient.setChannelLogHandler(new YunLogHandler() {
//			@Override
//			public void onHandle(YunLogEvent event) { 
//				logger.error("注册YunLogHandler类：{}",event.getMessage());
//			}
//		});
//        try { 
//        	// 4. 创建请求类对象 
//			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
//            request.setDeviceType(3); // device_type => 1: web 2: pc 3:android 4:ios 5:wp
//            
//            request.setChannelId(Long.valueOf(pushUserDevice.getChannel_id()));
//            request.setUserId(pushUserDevice.getBd_user_id());
//            request.setMessageType(1);
//            request.setMessage("{\"title\":\""+tiltle+"\",\"description\":\""+description+"\"}");
//            // 5. 调用pushMessage接口
//            PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
//            // 6. 认证推送成功
//            if(response.getSuccessAmount()==1){
//				logger.info("time:{} ,push amount :{}",System.currentTimeMillis(),response.getSuccessAmount());
//				return errorCode_server_success;
//			}else{
//				logger.error("time:{} ,push amount :{}",System.currentTimeMillis(),response.getSuccessAmount());
//				return errorCode_server_failed;
//			} 
//        } catch (ChannelClientException e) {
//			// 处理客户端错误异常
//        	e.printStackTrace();
//			logger.error("处理客户端错误异常:{}", e.getMessage());
//			return errorCode_client_exception;
//		} catch (ChannelServerException e) {
//			// 处理服务端错误异常
//			e.printStackTrace();
//			logger.error("处理服务端错误异常：request_id: {}, error_code: {}, error_message: {}",e.getRequestId(), e.getErrorCode(), e.getErrorMsg());
			return errorCode_server_exception;
//		}
    }
    
    /**
     * 
     * <b>Summary: </b>
     *    push2Notific 广播通知消息
     * String
     * @param user_id
     * @param channel_id
     * @param pushMsg
     * @return 
     * push2Notific
     * Apr 9, 2014
     */
    public static String push2Notific(String tiltle,String description){
//    	// 2.创建BaiduChannelClient对象实例
//		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
//		
//		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
//		// 3. 若要了解交互细节，请注册YunLogHandler类
//		channelClient.setChannelLogHandler(new YunLogHandler() {
//			@Override
//			public void onHandle(YunLogEvent event) { 
//				logger.error("注册YunLogHandler类：{}",event.getMessage());
//			}
//		});
//        try { 
//        	// 4. 创建请求类对象 
//        	PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
//            request.setDeviceType(3); // device_type => 1: web 2: pc 3:android 4:ios 5:wp
//            request.setMessageType(1);//通知
//            System.out.println("{\"title\":\""+tiltle+"\",\"description\":\""+description+"\"}");
//            request.setMessage("{\"title\":\""+tiltle+"\",\"description\":\""+description+"\"}");
//            // 5. 调用pushMessage接口
//            PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);
//            // 6. 认证推送成功
//            if(response.getSuccessAmount()==1){
//				logger.info("time:{} ,push amount :{}",System.currentTimeMillis(),response.getSuccessAmount());
//				return errorCode_server_success;
//			}else{
//				logger.error("time:{} ,push amount :{}",System.currentTimeMillis(),response.getSuccessAmount());
//				return errorCode_server_failed;
//			} 
//        } catch (ChannelClientException e) {
//			// 处理客户端错误异常
//        	e.printStackTrace();
//			logger.error("处理客户端错误异常:{}", e.getMessage());
			return errorCode_client_exception;
//		} catch (ChannelServerException e) {
//			// 处理服务端错误异常
//			e.printStackTrace();
//			logger.error("处理服务端错误异常：request_id: {}, error_code: {}, error_message: {}",e.getRequestId(), e.getErrorCode(), e.getErrorMsg());
//			return errorCode_server_exception;
//		}
    }
    
    
    private static String transformPushMsg(PushMsg pushMsg){
       StringBuffer msg = new StringBuffer();
 					msg.append("{")
					   .append("'cmd':'").append(pushMsg.getCmd()).append("'")
					   .append(",")
					   .append("'info':{'time_stamp':'").append(System.currentTimeMillis()).append("',")
					   .append("'from_id':'").append(StringUtil.parseNull(pushMsg.getFrom_id())).append("',")
					   .append("'from_name':'").append(StringUtil.parseNull(pushMsg.getFrom_name())).append("',")
					    //.append("'head_url':'").append(StringUtil.parseNull(pushMsg.getHead_url())).append("',")
					   .append("'group_ids':'").append(StringUtil.parseNull(pushMsg.getGroup_ids())).append("',")
					   .append("'group_names':'").append(StringUtil.parseNull(pushMsg.getGroup_names())).append("',")
					   .append("'msg_type':'").append(pushMsg.getMsg_type()).append("',")
					   .append("'msg_text':'").append(StringUtil.parseNull(pushMsg.getMsg_text())).append("',")
					   .append("'att_url':'").append(StringUtil.parseNull(pushMsg.getAtt_url())).append("',")
					   .append("'att_name':'").append(StringUtil.parseNull(pushMsg.getAtt_name())).append("',")
					   .append("'att_length':'").append("".equals(StringUtil.parseNull(pushMsg.getAtt_length()))?"0":StringUtil.parseNull(pushMsg.getAtt_length())).append("'")
					   .append("}}"); 
 		logger.info("推送消息:{}",msg.toString());
 		System.out.println(msg.toString());
    	return msg.toString();
    }
   
    public static String pussToAll(PushMsg pushMsg) throws Exception{
 // 1. get apiKey and secretKey from developer console
 		PushKeyPair pair = new PushKeyPair(dgbAndroidApiKey, dgbAndroidSecretKey);

 		// 2. build a BaidupushClient object to access released interfaces
 		BaiduPushClient pushClient = new BaiduPushClient(pair,BaiduPushConstants.CHANNEL_REST_URL);

 		// 3. register a YunLogHandler to get detail interacting information
 		// in this request.
 		pushClient.setChannelLogHandler(new YunLogHandler() {
 			@Override
 			public void onHandle(YunLogEvent event) {
 				System.out.println(event.getMessage());
 			}
 		});
 
 		try {
 		// 创建IOS通知
 					JSONObject notification = new JSONObject();
 					JSONObject jsonAPS = new JSONObject();
 					jsonAPS.put("alert", pushMsg.getMsg_text());
 					jsonAPS.put("sound", "ttt"); // 设置通知铃声样式,例如"ttt"，用户自定义。
 					notification.put("aps", jsonAPS);
 					notification.put("key1", "value1");
 					notification.put("key2", "value2");
 			// 4. specify request arguments
 			PushMsgToAllRequest request = new PushMsgToAllRequest()
 					.addMsgExpires(new Integer(3600))
 					.addMessageType(0)
 					.addMessage(transformPushMsg(pushMsg)) 
 					.addSendTime(System.currentTimeMillis() / 1000 + 65) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
 					.addDeviceType(3);
 			// 5. http request
 			PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
 			// Http请求结果解析打印
 			System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
 					+ response.getSendTime() + ",timerId: "
 					+ response.getTimerId());
 		} catch (PushClientException e) {
 			if (BaiduPushConstants.ERROROPTTYPE) {
 				throw e;
 			} else {
 				e.printStackTrace();
 			}
 			return errorCode_server_exception;
 		} catch (PushServerException e) {
 			if (BaiduPushConstants.ERROROPTTYPE) {
 				throw e;
 			} else {
 				System.out.println(String.format(
 						"requestId: %d, errorCode: %d, errorMessage: %s",
 						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
 			}
 			return errorCode_server_exception;
 		}
 		return errorCode_server_success;
    }
    private static String transformPushMsg2(PushMsg pushMsg){
	       StringBuffer msg = new StringBuffer();
	 					msg.append("{")
						   .append("\"info\":{\"time_stamp\":\"").append(System.currentTimeMillis()).append("\"")
						   .append(",")
						   .append("\"app_id\":\"").append(StringUtil.parseNull(pushMsg.getApp_id())).append("\",")
						   .append("\"from_id\":\"").append(StringUtil.parseNull(pushMsg.getFrom_id())).append("\",")
						   .append("\"from_name\":\"").append(StringUtil.parseNull(pushMsg.getFrom_name())).append("\",")
						   .append("\"msg_type\":\"").append(pushMsg.getMsg_type()).append("\",")
						   .append("\"msg_text\":\"").append(StringUtil.parseNull(pushMsg.getMsg_text())).append("\"")
						   .append("}}"); 
	 		logger.info("推送消息:{}",msg.toString()); 
	    	return msg.toString();
	    }
    public  static void main(String[] args){
//    	String[] user_ids = {,"1087215422625889091",""};
//    	long[] channel_ids = {,4109604894656263925L,L}; 
    	List<Object> list  = new ArrayList<Object>();
//    	PushUserDevice pushUserDevice = new PushUserDevice();
//    	pushUserDevice.setBd_user_id("711465141142661498");
//    	pushUserDevice.setChannel_id("3727072830899034810");
//    	pushUserDevice.setUser_id("yuanlei");
//    	list.add(pushUserDevice);
//        pushUserDevice = new PushUserDevice();
//    	pushUserDevice.setBd_user_id("1087215422625889091");
//    	pushUserDevice.setChannel_id("4109604894656263925");
//    	pushUserDevice.setUser_id("ccl");
//    	list.add(pushUserDevice);
//    	 pushUserDevice = new PushUserDevice();
//     	pushUserDevice.setBd_user_id("921330370717698071");
//     	pushUserDevice.setChannel_id("3693995291361388215");
//     	pushUserDevice.setUser_id("wlj");
//     	list.add(pushUserDevice);
//     	PushUserDevice push = new PushUserDevice();
//		push.setAppid("123");
//		push.setBd_user_id("695131387653287551");
//		push.setChannel_id("3693995291361388215");
//		list.add(push);
//		PushApi.push2Msg(new PushMsg("cmd_chat","1","储老板，你妈喊你回家吃饭"));
//    	System.out.println(PushApi.push2UserGroup(list, new PushMsg("cmd_chat","1","储老板，你妈喊你回家吃饭")));
//    	System.out.println(push2Notific("测试通知","回家吃饭了"));
		
		try {
			pussToAll(new PushMsg("cmd_chat","1","-----"));
//			push2All("");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
    }
    
    public static void push2All(String msg) throws PushClientException,PushServerException {
		// 1. get apiKey and secretKey from developer console
		PushKeyPair pair = new PushKeyPair(dgbIosApiKey, dgbIosSecretKey);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			// 创建IOS通知
			JSONObject notification = new JSONObject();
			JSONObject jsonAPS = new JSONObject();
			jsonAPS.put("alert", msg);
			jsonAPS.put("sound", "ttt"); // 设置通知铃声样式,例如"ttt"，用户自定义。
			jsonAPS.put("badge", "1");
			notification.put("aps", jsonAPS);
			notification.put("key1", "value1");
			notification.put("key2", "value2");
			
			PushMsgToAllRequest request = new PushMsgToAllRequest()
					.addMsgExpires(new Integer(3600))
					.addMessageType(1)
					.addMessage(notification.toString())
					.addSendTime(System.currentTimeMillis() / 1000 + 70) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDepolyStatus(2)
					.addDeviceType(4);
			// 5. http request
			PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
			// Http请求结果解析打印
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime() + ",timerId: "
					+ response.getTimerId());
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}
}
 
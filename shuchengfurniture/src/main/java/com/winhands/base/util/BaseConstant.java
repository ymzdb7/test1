package com.winhands.base.util;

/**
 * 系统静态常量
 * 
 * @author yuanlei
 * 
 */
public class BaseConstant { 
	public static final String app_name = "家具生产安全平台";

	public static final String clientType = "client";// 手机 
	
	public static final String super_men = "100000000000001";
	 
	public static final boolean debug = false; 
	
	public static final int pageSize = 10; 
	
	public static final String fileUploadPath = "/upload/";
	public static final String fileUploadPathZip = "/upload/file/";
	public static final String fileUploadPromisePath = "/upload/promise/";
	public static final String fileUploadSecurityPath = "/upload/security/";
	public static final String fileUploadGuidePath = "/upload/guide/";
	public static final String fileUploadCarouselPath = "/upload/carousel/";//滚动活动相关文件
	public static final String fileUploadInfoPath = "/upload/info/";//资讯相关文件
 	public static final int SALT_SIZE = 8; 
 	//192.168.10.105
 	public static final String REDISHOST = "localhost";
 	public static final int REDISPORT = 6379;

	public static final String ACCOUNT = "N6761275";  //短信服务账号
	public static final String PSWD = "UziC9Rbg7P9d4c";    //短信服务密码
	public static final String SMSURL = "http://222.73.117.169:80/msg/HttpBatchSendSM";   //短信发送接口地址
	public static final String PRE_SMS = "家具生产安全";   //短信抬头
	public static final int SMS_COUNT = 10;  //一天内同一个手机号可发短信次数

	public static final String AVATAR_PATH = "/upload/avatar";
	
	public static final int maxTime = 60000;
 	
 	public static final String alipay = "alipay"; 
 	public static final String wechat = "wechat";
 	public static final String API_KEY="mKLFMA34LKFGLZlvmlxvna4fl2sFrls2";
 	public static final String APP_ID="wxbfb1f1e926c50609";//应用appId
 	public static final String MCH_ID="1396419202";//商户号：1396419202
 	//积分规则id
 	public static final String JFDZID = "8751483132080096487"; //积分点赞id
 	public static final String JFYDID = "9066078346192214227"; //积分阅读id
 	public static final String JFPLID = "8851964684022572672"; //积分点留言评论id
 	public static final String JFDTID = "5320062384250166853"; //积分回答问题id
}

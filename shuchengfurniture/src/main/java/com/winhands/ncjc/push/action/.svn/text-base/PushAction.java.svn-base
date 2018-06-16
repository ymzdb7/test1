package com.winhands.ncjc.push.action;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.winhands.base.user.entity.User;
import com.winhands.base.util.BaseJson;
import com.winhands.base.util.DateUtil;
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;
import com.winhands.ncjc.push.PushApi;
import com.winhands.ncjc.push.entity.PushMsg;
import com.winhands.ncjc.push.entity.PushUserDevice;
import com.winhands.ncjc.push.entity.PushUserDeviceBean;
import com.winhands.ncjc.push.service.PushApiService;
@Component
@RequestMapping("/push")
public class PushAction extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(PushAction.class); 
	private static final long serialVersionUID = -3263018348027599648L;
	@Autowired
	private PushApiService pushApiService;
	
	
	
	//注册接口
	@ResponseBody
	@RequestMapping("/registerPush")  
	public BaseJson  registerPush( PushUserDevice pushUserDevice,String user_id,String bd_user_id){
		if(StringUtil.isNull(pushUserDevice.getAppid())
				||StringUtil.isNull(bd_user_id)
				||StringUtil.isNull(pushUserDevice.getChannel_id())
				||StringUtil.isNull(pushUserDevice.getDevice_type())
				||StringUtil.isNull(user_id)){
			return new BaseJson(600,"参数无效","");
		}else{
			try {
				PushUserDeviceBean pushUserDeviceBean=new PushUserDeviceBean();
				pushUserDeviceBean.setBd_user_id(bd_user_id);
				pushUserDeviceBean.setUser_id(user_id);
				pushUserDevice.setPushUserDeviceBean(pushUserDeviceBean);
				pushUserDevice.setCreate_time(new Date());
				pushApiService.saveUser2Push(pushUserDevice);
				return new BaseJson(1,"注册成功","");
			} catch (Exception e) {
				e.printStackTrace();
				return new BaseJson(600,"注册失败","");
			}
		}
	}
	@ResponseBody
	@RequestMapping("/sendMsg2SingelUser")  
	public BaseJson sendMsg2SingelUser(PushMsg pushMsg){
		if(StringUtil.isNull(pushMsg.getApp_id())
				||StringUtil.isNull(pushMsg.getCmd())
				||StringUtil.isNull(pushMsg.getMsg_type())
				||StringUtil.isNull(pushMsg.getFrom_id())
				||StringUtil.isNull(pushMsg.getGroup_ids())){
			return new BaseJson(600,"参数无效","");
		}else{
			try {
				PushUserDevice pushUserDevice = new PushUserDevice();
				 Map<String,Object>  pushMap = new HashMap<String,Object>();
				 pushMap.put("user_id", pushMsg.getGroup_ids());
				 pushMap.put("appid", pushMsg.getApp_id());
				pushUserDevice = pushApiService.findByPushUserDevice(pushMap);
				if(pushUserDevice==null){
					return new BaseJson(0,"该用户无匹配推送注册信息，请检查user_id和app_id是否正确","");
				}else{
					//推送
					String msgId = PushApi.push2UserMsg(pushUserDevice, pushMsg);
					if(!PushApi.errorCode_server_failed.equals(msgId)){
						pushMsg.setMsg_id(msgId);
					}else{
						pushMsg.setMsg_status("-9");//失败
					}
					pushMsg.setSn(StringUtil.getUUIDString());//设置消息流水号
					pushMsg.setTime_stamp(new Date());
		            pushMsg.setCreate_time(new Date());//设置创建时间 
		            pushMsg.setDevice_type(pushUserDevice.getDevice_type());//保存设备类型
					
					if("-9".equals(pushMsg.getMsg_status())){
						return new BaseJson(0,"发送失败，请重试!","");
					}else{
						pushMsg = pushApiService.savePushMsg(pushMsg);
						//jsonString = installJsonString(1, msgId);
						if(pushMsg!=null){
							return new BaseJson(1,msgId,"");
							//PushApi.queryMsgStatus("4372688672568480651",pushUserDevice.getDevice_type());
						}else{
							return new BaseJson(0,"消息推送成功，保存流水失败。","");
						} 
					} 
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				logger.error("系统异常", e.getMessage());
				return new BaseJson(0,"发送失败，请重试!","");
			} 
		}
		
	}
	@ResponseBody
	@RequestMapping("/sendXmlMsg2SingelUser")
	public BaseJson sendXmlMsg2SingelUser(HttpServletRequest request,String from_name,String user_name,
			String msg_title){ 
		StringBuffer xml = new StringBuffer();
	    String line = null;
	    String msg_type;
	    String app_id;
	    String cmd;
	    String from_id;
	    String user_id;
	    String msg_text;
		try{
		   BufferedReader reader = request.getReader();
		   while((line=reader.readLine())!=null){
		     xml.append(line);
		   }
		   String filePath = "/opt/data/";
		   FileOutputStream fos = new FileOutputStream(filePath+ "/msg.xml");
		   OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		   osw.write(xml.toString());
		   osw.flush();
		   fos.close(); 
		   
		   msg_type = "0";
		   app_id = "gwb";
		   cmd = "notice";
		   from_id="crm";
		   SAXReader xmlReader = new SAXReader();
		   Document document = xmlReader.read(new StringReader(xml.toString()));
		   Element rootElm = document.getRootElement();
		   List<User> nodes = new ArrayList<User>();
		   Element ele;  
		   for (Iterator<Element> i = rootElm.elementIterator("row");i.hasNext();) {
				ele=i.next();
				User user = new User();
					
					user_id = ele.elementText("LOY_MEM_ID");
					
					msg_text = ele.elementText("TEXT");  
					
					if(StringUtil.isNull(app_id)||StringUtil.isNull(cmd)||StringUtil.isNull(msg_type)||StringUtil.isNull(from_id)||StringUtil.isNull(user_id)){
						return new BaseJson(0,"参数有误!","");
					}else{
						PushMsg pushMsg = new PushMsg();
						pushMsg.setApp_id(app_id);
						pushMsg.setCmd(cmd);
						pushMsg.setFrom_id(from_id);
						pushMsg.setFrom_name(from_name);
						pushMsg.setGroup_ids(user_id);
						pushMsg.setGroup_names(user_name);
						pushMsg.setMsg_type(msg_type);
						pushMsg.setMsg_text(msg_text);
						pushMsg.setMsg_title(msg_title);
						try {
							PushUserDevice pushUserDevice = new PushUserDevice();
							Map<String,Object>  pushMap = new HashMap<String,Object>();
							 pushMap.put("user_id", pushMsg.getGroup_ids());
							 pushMap.put("appid", pushMsg.getApp_id());
							pushUserDevice = pushApiService.findByPushUserDevice(pushMap);
							if(pushUserDevice==null){
								return new BaseJson(0, "该用户无匹配推送注册信息，请检查user_id和app_id是否正确", "");
							}else{
								//推送
								String msgId = PushApi.push2UserMsg(pushUserDevice, pushMsg);
								if(!PushApi.errorCode_server_failed.equals(msgId)){
									pushMsg.setMsg_id(msgId);
								}else{
									pushMsg.setMsg_status("-9");//失败
								}
								pushMsg.setSn(StringUtil.getUUID());//设置消息流水号
								pushMsg.setTime_stamp(new Date());
					            pushMsg.setCreate_time(new Date());//设置创建时间 
					            pushMsg.setDevice_type(pushUserDevice.getDevice_type());//保存设备类型
								
								if("-9".equals(pushMsg.getMsg_status())){
									return new BaseJson(0, "发送失败，请重试!", "");
								}else{
									pushMsg = pushApiService.savePushMsg(pushMsg);
									//jsonString = installJsonString(1, msgId);
									if(pushMsg!=null){
										return new BaseJson(1, msgId, "");
									}else{
										return new BaseJson(0, "消息推送成功，保存流水失败", "");
									} 
								} 
							}
							
						}catch (Exception e) {
							logger.error("发送失败，请重试", e.getMessage());
							return new BaseJson(0, "发送失败，请重试!", "");
						} 
					} 
			}  
		   return new BaseJson(1, "ok!", "");
		}catch(Exception e){
		   //e.printStackTrace();
		   logger.error("读取xml数据异常", e.getMessage());
		   return new BaseJson(0, "异常!", "");
		} 
	}
	
	
	@ResponseBody
	@RequestMapping("/queryUserMsg")
	public BaseJson queryUserMsg(String user_id,String from_id,Integer page,Integer rows,
			String msg_type,String msg_title,String msg_text){
		try {
			if (StringUtil.isNull(user_id) || page == null || rows == null) {
				return new BaseJson(0, "参数不正确!", "");
			} else {
				PushMsg pushMsg = new PushMsg();
				Map<String, Object> pushMap = new HashMap<String, Object>();
				pushMap.put("group_ids", user_id);
				pushMap.put("msg_type", pushMsg.getGroup_ids());
				pushMap.put("msg_title", msg_title);
				pushMap.put("msg_text", msg_text);
				//设置查询时间  查询前90天消息历史记录
				pushMap.put("create_time", DateUtil.addDate(new Date(), -90));
				Page<PushMsg> page2 = new Page<PushMsg>();
				PageHelper.startPage(page, rows);
				page2 = (Page<PushMsg>) pushApiService.queryUserMsg(pushMap);
				return new BaseJson(1, "查询成功", "", page2);
			}
		} catch (Exception e) {
			return new BaseJson(0, "查询失败!", "");
		}
	}
	@ResponseBody
	@RequestMapping("/querySendMsg")
	public BaseJson querySendMsg(String user_id,String from_id,Integer page,Integer rows,
			String msg_type,String msg_title,String msg_text){
		try {
			if (StringUtil.isNull(user_id) || StringUtil.isNull(from_id)
					|| page == null || rows == null) {
				return new BaseJson(0, "参数不正确!", "");
			} else {
				PushMsg pushMsg = new PushMsg();
				pushMsg.setGroup_ids(user_id);//必填参数 
				pushMsg.setFrom_id(from_id);//必填参数
				pushMsg.setMsg_type(msg_type);
				pushMsg.setMsg_title(msg_title);
				pushMsg.setMsg_text(msg_text);
				Map<String, Object> pushMap = new HashMap<String, Object>();
				pushMap.put("user_id", pushMsg.getGroup_ids());
				pushMap.put("user_id", pushMsg.getGroup_ids());
				pushMap.put("user_id", pushMsg.getGroup_ids());
				pushMap.put("user_id", pushMsg.getGroup_ids());
				pushMap.put("user_id", pushMsg.getGroup_ids());
				pushMap.put("user_id", pushMsg.getGroup_ids());
				Page<PushMsg> page2 = new Page<PushMsg>();
				PageHelper.startPage(page, rows);
				page2 = (Page<PushMsg>) pushApiService.queryUserMsg(pushMap);
				return new BaseJson(1, "查询成功", "", page2);
			}
		} catch (Exception e) {
			return new BaseJson(0, "查询失败!", "");
		} 
		
	}
	
	
}

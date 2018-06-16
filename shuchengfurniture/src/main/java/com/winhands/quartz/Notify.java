package com.winhands.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.winhands.base.util.StringUtil;
import com.winhands.cshj.article.entity.Article;
import com.winhands.cshj.article.service.ArticleService;
import com.winhands.ncjc.push.PushApi;
import com.winhands.ncjc.push.entity.PushMsg;
import com.winhands.ncjc.push.service.PushApiService;

public class Notify {
	public static final Logger logger = LoggerFactory.getLogger(Notify.class); 
	@Autowired
	private ArticleService articleService;
	@Autowired
	private PushApiService pushApiService;
	
	
	//同步推送到百度推送
	public void work() {
		List<Article>  list = articleService.queryNotifyArticleList(new HashMap());
		Date date = new Date();
		for(Article article:list){ 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			try {
				Date onlinedate = sdf.parse(article.getOnline_date()+":00"); 
				long dayNum =  onlinedate.getTime() - date.getTime();// DateUtil.differenceOfDay(new Date() , onlinedate);
				if(dayNum<=0){//已上线
					//推送
					String text ="标题："+article.getTitle()+",作者："+article.getAuthor_name();
					//安卓推送
					PushMsg pushMsg=new PushMsg(StringUtil.getUUIDString(), PushApi.dgbAndroidApiKey, new Date(), "舒晨文化传媒", "0", text);
					//ios推送
					PushMsg pushMsgIos=new PushMsg(StringUtil.getUUIDString(), PushApi.dgbIosApiKey, new Date(), "舒晨文化传媒", "0", text);
			    	try {
			    		//ios  android
						PushApi.pussToAll(pushMsg);//推送安卓平台
						System.out.println("-------------------------------------------");
						pushMsgIos.setMsg_text(text);
						PushApi.push2All(text);//推送安卓平台
			    		articleService.updateArticleNotify(article); 
					} catch (Exception e) {
						logger.info("推送失败");
						e.printStackTrace();
					}
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			} 
		}
		System.out.println("同步推送到百度推送");
		
	} 
}

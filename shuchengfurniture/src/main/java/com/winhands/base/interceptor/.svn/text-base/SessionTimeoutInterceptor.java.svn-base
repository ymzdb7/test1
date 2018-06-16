package com.winhands.base.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.logicalcobwebs.asm.tree.TryCatchBlockNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.winhands.base.exception.SessionTimeoutClientException;
import com.winhands.base.exception.SessionTimeoutException;
import com.winhands.base.shiro.BaseRealm.ShiroUser;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.DESPlus72;
import com.winhands.base.util.StringUtil;
import com.winhands.cshj.article.entity.Article;
import com.winhands.cshj.article.service.ArticleService;
import com.winhands.cshj.member.entity.Member;
import com.winhands.cshj.member.service.MemberService;
/**
 * 登录校验
 * @author yuanl
 *
 */
public class SessionTimeoutInterceptor extends HandlerInterceptorAdapter {
	   private final Logger logger = LoggerFactory.getLogger(SessionTimeoutInterceptor.class); 
	   private List<String> allowUrls; 
	   @Autowired 
	   private ArticleService articleService;
	   @Autowired
	   private MemberService memberService;
	    
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        boolean flag = false;
	        String url = request.getRequestURL().toString();
	        String user_id2 = request.getParameter("user_id");
	        String from=request.getParameter("from");
	        logger.info("权限校验!{}",url); 
	        if(url.contains("/upload/file/")){
	        	//对于增加form的字段的请求，首先认为这些都是来自于客户端请求,没有form字段都是来自于pc端
		        if(!StringUtil.isNull(from)){
		        	//对于form值不是client的，作特殊处理
		        	//如果是来自于微信分享，则全部通过,对于非html这类资源文件全部放过
		        	if(url.endsWith(".html")){
		        		if(from.equals("share")||from.equals("singlemessage")||from.equals("timeline")||from.equals("groupmessage")){ 
			        		flag = true;
			        	}else{
			        		//根据文章类型和用户类型，来判断是否能看
     		        		//先查询文章是否是会员文章，若不是，则查询该用户是否为会员 
			        		String [] str1 = url.split("/article/");
			        		String tempIdStr = str1[1];
			        		String[]str2= tempIdStr.split("/");
			        		String article_id=str2[0];
			        		Article articleTemp=new Article();
			        		articleTemp = articleService.findById(article_id);
			        		if(articleTemp!=null){
			        			if("1".equals(articleTemp.getRead_limit())){//非注册用户文章不做拦截
			        				logger.info("read_limit:"+articleTemp.getRead_limit());
			        				flag = true; 
			        			}else if("5".equals(articleTemp.getRead_limit())){//注册用户文章，只需要判断登录超时 
			        				 //用户id不为空，安卓这样传参,ios直接走会话超时
		            				 if(!StringUtil.isNull(user_id2)){
		            					 //解密用户id
			            				 user_id2 = DESPlus72.decrypt(user_id2);
			            				 //根据user_id
			        	        		 if(user_id2.equals("999")){//
			        	        			throw new SessionTimeoutException();
			        	        		 }else{
			        	        			Member member = memberService.queryMemberById(user_id2); 
			        	        			if(member!=null){ 
			            	        			flag = true;
			        	        			}else{
			        	        				throw new SessionTimeoutException();
			        	        			} 
			        	        		 }
		            				 } 
			        			}else {
			        				if(!StringUtil.isNull(user_id2)){
			    	        			//解密用户id
			            				user_id2=DESPlus72.decrypt(user_id2);
			        	        		//根据user_id
			        	        		if(user_id2.equals("999")){//
			        	        			throw new SessionTimeoutException();
			        	        		}else{
			        	        			Member member = memberService.queryMemberById(user_id2);
			        	        			if(member.getType().equals("2")){ 
			            	        			flag = true;
			        	        			}else{
			        	        				throw new SessionTimeoutException();
			        	        			}
			        	        			
			        	        		}
			    	        		}else{ 
			    	        			if(SecurityUtils.getSubject().isAuthenticated()){
			    	        				ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			    	        				user_id2= user.userId;
			    	        				Member member = memberService.queryMemberById(user_id2);
			        	        			if(member.getType().equals("2")){ 
			            	        			flag = true;
			        	        			}else{
			        	        				throw new SessionTimeoutException();
			        	        			}
			    	       			    } 
			    	        		}
			        			}
			        		}
			        		
			        	} 
		        	}else{
		        		flag = true; 
		        	} 
		        }else{
		        	if(!url.endsWith(".html")){
		        		flag = true; 
		        	}
		        } 
	        }  
	        
	        for (String s : allowUrls) {  
	            if (url.contains(s)) {
	                flag = true;
	                break;
	            }
	        } 
            if(!flag){ 
            	if(SecurityUtils.getSubject().isAuthenticated()){
    				 flag = true;
    			}else{
    				String form = request.getParameter("from");
    				if (BaseConstant.clientType.equals(form)){
    					throw new SessionTimeoutClientException();
    				}else{
    					throw new SessionTimeoutException();
    				} 
    			} 
            } 
            return flag;
			
	    }
	 
	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	        super.postHandle(request, response, handler, modelAndView);
	    }

		public List<String> getAllowUrls() {
			return allowUrls;
		}

		public void setAllowUrls(List<String> allowUrls) {
			this.allowUrls = allowUrls;
		}
	    
	    
}

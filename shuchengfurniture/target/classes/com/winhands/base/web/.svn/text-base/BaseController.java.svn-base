package com.winhands.base.web;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import com.winhands.base.login.web.LoginController;
import com.winhands.base.shiro.BaseRealm.ShiroUser;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.PageBt;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
 
public class BaseController<T> {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	public PageBt pageBt  = new PageBt();
    public static JedisPool jedisPool;  //redis连接池
	
	public static JedisPool getJedisPool() {
		logger.info("getJedisPool");
        if (jedisPool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            jedisPool = new JedisPool(config, BaseConstant.REDISHOST , BaseConstant.REDISPORT,10000);
        }
        logger.info("结束");
        return jedisPool;
    }
	/**获得user session**/
	public static ShiroUser getUserSession(){
		//Users user = (Users)SecurityUtils.getSubject().getSession().getAttribute(BaseConstant.USER_SESSION_KEY);
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}
	 
	public PageBt page2PageBt(Page<T> page){
		return new PageBt(page.getNumber()+1, page.getSize(), page.getTotalElements());
	} 
	
	public PageBt page2PageBt(com.github.pagehelper.Page<T> page){
		return new PageBt(page.getPageNum(), page.getPageSize(),page.getTotal() );
	}
}

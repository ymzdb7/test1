package com.winhands.base.auth.repository.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.winhands.base.auth.entity.Menu;
import com.winhands.base.login.web.LoginController;
import com.winhands.base.web.BaseController;

public class RedisDao {
    private final Logger logger = LoggerFactory.getLogger(RedisDao.class);
    private JedisPool pool;
    private RuntimeSchema<Menu> schema = RuntimeSchema.createFrom(Menu.class);

    public RedisDao() {
    	logger.info("获取缓存开始");
        pool = BaseController.getJedisPool();
        logger.info("获取缓存结束");
    }

    public Menu getMenu(String userId) {
        Jedis jedis = pool.getResource();
        try {
        	logger.info("redis逻辑操作,menu:{}",userId);
            // redis逻辑操作
            String key = "menu:" + userId;
            // 并没有实现内部序列化操作
            // get:byte[]->反序列化->Object(Menu)
            // 采用自定义序列化
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes != null) {
                // 空对象
                Menu menu = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes, menu, schema);
                // menu 被反序列化
                return menu;
            } 
            logger.info("redis逻辑操作结束,menu:{}",userId);
        } finally{
            jedis.close();
        }
        return null;
    }

    /**
     * Menu 对象传递到redis中
     *
     * @param menu
     * @return
     */
    public String putMenu(Menu menu, String userId) {
        // set:Object(Menu)->序列化->byte[] ->发送给redis
        try {
        	logger.info("redis序列化,putMenu:{}",userId);
            Jedis jedis = pool.getResource();
            try {
                String key = "menu:" + userId;
                byte[] bytes = ProtostuffIOUtil.toByteArray(menu, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                // 超时缓存
                int timeOut = 60 * 60;  //1小时
                String result = jedis.setex(key.getBytes(), timeOut, bytes);
                logger.info("redis序列化,putMenu:{}结束",userId);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;

    }

}

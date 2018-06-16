package com.winhands.cshj.emp;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Test {

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        Set<String> list = jedis.keys("*");
        for(String str : list){
            System.out.println(str);
        }

    }

}

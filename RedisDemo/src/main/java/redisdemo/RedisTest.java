package redisdemo;

import redis.clients.jedis.Jedis;
import java.util.List;
import java.util.Set;

/**
 * @author wangdian
 * @package redisdemo
 * @date 2020/9/28
 * @time 10:53
 */
public class RedisTest {
    public static Jedis jedis;
    public static void main(String[] arg){
        connectRedis();
        redisString();
        redisList();
        redisKey();
    }

    public static void connectRedis(){
        // 连接本地Redis服务
        jedis = new Jedis("localhost");
        jedis.auth("hogwarts");
        System.out.println("连接成功");
        System.out.println("服务正常运行："+jedis.ping());
    }

    public static void redisString(){
        jedis.set("hogwarts_key","霍格沃兹测试学院");
        System.out.println("redis 存储字符串为："+ jedis.get("hogwarts_key"));
    }

    public static void redisList(){
        jedis.lpush("hogwarts-list","霍格沃兹测试学院");
        jedis.lpush("hogwarts-list","学院名单");
        jedis.lpush("hogwarts-list","张三");
        jedis.lpush("hogwarts-list","李四");

        List<String> list = jedis.lrange("hogwarts-list", 0, 2);
        for (String s : list) {
            System.out.println("列表项为：" + s);
        }
    }

    public static void redisKey(){
        Set<String> keys = jedis.keys("*");
        System.out.println("所有键值：");
        for(String key:keys){
            System.out.println(key);
        }
    }
}

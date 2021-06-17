package redis.api;

import redis.clients.jedis.Jedis;

/**
 * @ClassName RankRedis
 * @Author bin
 * @Date 2021/3/31 上午10:19
 * @Decr TODO
 *  排行榜- redis
 *  基于sortSet类型
 * @Link TODO
 **/
public class RankRedis {
    private static Jedis jedis = new Jedis("localhost");

    private static String key = "rankKey";

    public static void v1 () {
        //添加元素
        jedis.zadd(key,1,"a");
        jedis.zadd(key,2,"b");
        jedis.zadd(key,3,"c");
        jedis.zadd(key,4,"d");

        //更新元素的分数 - 将元素分数增加
        jedis.zincrby(key,2,"a");

        //查询某个元素
        jedis.zscore(key,"a");
    }
}

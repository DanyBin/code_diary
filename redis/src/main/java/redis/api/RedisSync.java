package redis.api;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.util.SafeEncoder;

/**
 * @ClassName RedisSync
 * @Author bin
 * @Date 2021/3/30 下午8:25
 * @Decr TODO
 *      redis分布式锁
 *
 *      1. 开源分布式锁 - Redisson
 *      2. 集群模型下的锁算法-RedLock
 *
 * @Link TODO
 **/
public class RedisSync {
    private static Jedis jedis = new Jedis("localhost");


    //释放锁的方式。基于Lua脚本，保证多个命令的原子性
    public static final String COMPARE_AND_DEL = "local cV = redis.call('get', KEYS[1]); "
            + "if cV == ARGV[1] "
            + "or (tonumber(ARGV[1]) == 0 and cV == false) then "
            + " redis.call('del', KEYS[1]); "
            + "return 1 "
            + "else "
            + "return 0 "
            + "end";

    public static final byte[] COMPARE_AND_DEL_BYTES = SafeEncoder.encode(COMPARE_AND_DEL);

    public static void v1() {
        //全局唯一
        String key = "request-key";
        String value = "requestId"; //traceId or host+ip+appkey
        SetParams setParams = new SetParams();
        setParams.nx(); //设置互斥
        setParams.ex(2);//2秒后过期

        //获取锁成功的情况
        try {
            if (1 == Integer.valueOf(jedis.set(key, value, setParams))) {
                //do something
            } else {
                System.out.println("获取锁失败");
            }
        } finally {
            //保证原子性
            jedis.eval(COMPARE_AND_DEL_BYTES);
        }

        //todo 其它问题
        /**
         * 1. 执行时常超时的情况
         * 2. 阻塞获取锁
         * 3. 锁具备可重入性
         */
    }


}

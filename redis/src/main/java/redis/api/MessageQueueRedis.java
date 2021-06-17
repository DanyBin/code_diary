package redis.api;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.List;

/**
 * @ClassName MessageQueueRedis
 * @Author bin
 * @Date 2021/3/31 上午9:55
 * @Decr TODO
 *      基于Redis的消息队列
 *      1。 基于List类型
 * @Link TODO
 **/
public class MessageQueueRedis {

    private static Jedis jedis = new Jedis("localhost");

    private static String key = "lkey";

    //Channel- 类型Topic
    private static String topic = "topic1";

    public void producer() {
        //生成元素 - 头插法 - 写入 == 批量写
        jedis.lpushx(key,"1","2","3");

        //push到channel 。== 最后更新消息
        jedis.publish(topic,"1");

    }

    public void  consumer () {
        //订阅channel - 消费
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                //备份机制
                jedis.rpoplpush(key,"lkeyback");

                //消费- 队尾元素 - 非阻塞时，轮训
                String value1 = jedis.rpop(key);

                //消费 - 队尾元素 - 阻塞时
                List<String> value2 = jedis.brpop(2, key);
            }
        }, topic);
    }
}

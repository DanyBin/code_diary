package redis.api;

import redis.clients.jedis.*;

import java.util.*;

/**
 * @ClassName ApiTest
 * @Author bin
 * @Date 2021/3/30 下午4:13
 * @Decr TODO
 * @Link TODO
 **/
public class ApiTest {
    private static Jedis jedis = new Jedis("localhost");

    public static void main(String[] args) {
        System.out.println("连接测试"+jedis.ping());
        key();
        System.out.println("------------------------");
        stringType();
        System.out.println("------------------------");
        hashType();
        System.out.println("------------------------");
        listType();
        System.out.println("------------------------");
        setType();
        System.out.println("------------------------");
        sortedSetType();
    }

    /**
     * 对于key的常用api
     */
    public static void  key (){
        String key = "str";

        jedis.set(key,"str类型测试");
        System.out.println(jedis.get(key));

        //序列化key
        byte[] strs = jedis.dump(key);

        //是否存在
        jedis.exists(key);

        //过期时间
        jedis.expire(key,2);

        // 过期时间的时间戳
        jedis.expireAt(key,System.currentTimeMillis()+1000);

        //以毫秒时间返回-剩余的过期时间。
        Long pttl = jedis.pttl(key);
        System.out.println("过期时间:毫秒"+pttl);

        //以秒级别返回-剩余的过期时间
        System.out.println("过期时间:秒"+jedis.ttl(key));

        //修改key的名称
        jedis.rename(key,key);


        //返回value对应的类型
        System.out.println("value对应的类型"+jedis.type(key));

        //移除过期时间，永久保存key
        jedis.persist(key);


        //删除key
        jedis.del(key);
    }

    /**
     * String类型的常用API
     */
    public static void stringType() {
        String key = "stringType";
        String value = "stringValue";

        String key1 = "stringType1";
        String value1 = "stringValue1";

        String key2 = "stringType2";

        //设置指定的key的值
        jedis.set(key,value);
        jedis.set(key1,value1);

        //获取key对应的值
        System.out.println("获取key对应的值"+jedis.get(key));

        //对value进行截断
        System.out.println("对value进行截断"+jedis.getrange(key,0,5));

        //获取偏移量上的bit位
        System.out.println(jedis.getbit(key,3));

        //批量获取key 对应的value
        String[] strArr = new String[2];
        strArr[0] = key;
        strArr[1] = key1;
        List<String> mget = jedis.mget(strArr);
        System.out.println("批量获取key-value:"+mget.toString());


        //将value -> key 并设置key的过期时间 会替换value
        jedis.setex(key,2,"string");

        //已毫秒的方式设置，过期时间 - 会替换value
        jedis.psetex(key,200,"stringMill");

        //当key不存在时，才设置key
        System.out.println("nx - 互斥使用" + jedis.setnx("kkkk", ""));

        //批量设置key。如果key不存在
        //jedis.msetnx(key,key1);


        //返回key -> value的长度
        System.out.println(jedis.strlen(key));

        //批量设置多个key-value
        String mset = jedis.mset("k1", "v1", "k2", "v2", "k3", "v3");
        System.out.println(mset);


        //todo 将key中存储的数子值增1. value必须是integer
//        Long incr = jedis.incr(key);
//        System.out.println(incr);
//
//        //数子值 -1 value必须是integer
//        Long decr = jedis.decr(key);
//        System.out.println(decr);

        //append 方法，追加
        jedis.append(key,"append");
        System.out.println(jedis.get(key));
    }

    /**
     * hash 类型.
     * 所有的操作，其它都加h。标示hash
     */
    public static void  hashType() {
        String key = "hashKey";
        String key1 = "hashKey1";
        String key2 = "hashKey2";
        String key3 = "hashKey3";

        //设置hashValue
        Map<String,String> map  = new HashMap<>();
        map.put("name","hash");
        map.put("type","hashType");
        map.put("incr","1");
        jedis.hmset(key,map);


        //key-> Mapkey 是否存在
        System.out.println("key-> Mapkey 是否存在: " + jedis.hexists(key,"name"));

        //key -> AllValue
        Map<String, String> result1 = jedis.hgetAll(key);
        System.out.println("key -> AllValue:" + result1.toString());


        //key -> MapKey = value + 10
        jedis.hincrBy(key,"incr",10);


        //获取所有map的key
        Set<String> hkeys = jedis.hkeys(key);
        System.out.println("获取所有map的key:"+hkeys.toString());

        //获取字段的数量
        Long hlen = jedis.hlen(key);
        System.out.println("获取字段的数量:"+hlen);

        //获取所有给MapKey的值
        List<String> hmget = jedis.hmget(key, "name", "incr");
        System.out.println(String.format("name对应的值%s,incr对应的值%s",hmget.get(0),hmget.get(1)));

        //更新Mapkey对应的值
        jedis.hset(key,"name","newHash");


        //如果MapKey不存在，在进行设置
        Long hsetnx = jedis.hsetnx(key,"name","tmp");
        Long hsetnx1 = jedis.hsetnx(key1, "key1", "value1");
        System.out.println(String.format("设置失败:%s 设置成功%s",hsetnx,hsetnx1));


        //获取key对应的Map中的value
        List<String> hvals = jedis.hvals(key);
        System.out.println("key对应的Map中的value:"+hvals);
    }

    /**
     * 列表操作
     */
    public static void listType() {
        String key = "listKey";
        String key1 = "listKey1";

        //添加元素. 支持多个元素。头插入法
        jedis.lpush(key,"1","2","3");

        //新插入元素-放在已有列表的头部
        jedis.lpushx(key,"4");

        //正常插入，放在尾部
        jedis.rpush(key,"6");
        //为已存在的列表添加值
        jedis.rpushx(key,"7");

        //获取指定范围内的元素
        List<String> lrange = jedis.lrange(key, 1, 10);
        System.out.println("指定范围内的元素："+lrange.toString());


        //通过索引获取列表中的元素
        String value = jedis.lindex(key, 2);
        System.out.println(String.format("index:2 value:%s",value));

        //在列表的元素前插入. 在 2 之前插入12
        jedis.linsert(key,ListPosition.BEFORE,"2","12");
        //在 2 之后插入 13
        jedis.linsert(key,ListPosition.AFTER,"2","13");

        List<String> result = jedis.lrange(key, 1, 10);
        System.out.println("获取插入前后的排序:"+result);

        //获取列表的长度
        Long llen = jedis.llen(key);
        System.out.println("列表的长度:"+llen);

        //阻塞列表移除 第一个元素. 有超时
        jedis.blpop(2,key);

        //阻塞列表移除，最后一个元素，有超时
        jedis.brpop(2,key);


        //移除&获取第一个元素
        String lpop = jedis.lpop(key);
        System.out.println("移除&获取第一个元素:"+lpop);

        //移除&获取最后一个元素
        String rpop = jedis.rpop(key);
        System.out.println("移除&获取最后一个元素"+rpop);


        //移除列表的元素
        System.out.println("移除前的结果:"+jedis.lrange(key,1,10));
        jedis.lrem(key,2,"-1");
        System.out.println("移除列表元素的第2位。之后的结果:"+jedis.lrange(key,1,10));


        //通过索引设置元素
        jedis.lset(key,2,"102");


        //移除列表最后一个元素，并添加到两一个列表中 & 插入到头部
        String rpoplpush = jedis.rpoplpush(key, key1);
        System.out.println("移除列表最后一个元素，并添加到两一个列表中 & 插入到头部:"+rpoplpush);
    }

    /**
     * Set类型的操作
     */
    public static void setType() {
        String key = "setKey";
        String key1 = "setKey1";


        //添加元素
        jedis.sadd(key,"1","2","3");
        jedis.sadd(key1,"2","3","4");


        //获取SetSize
        Long scard = jedis.scard(key);
        System.out.println("获取SetSize:"+scard);

        //获取集合之间的差异
        Set<String> sdiff = jedis.sdiff(key, key1);
        System.out.println("集合之间的差异:"+sdiff.toString());

        //获取key与key1的差集。并存放在 setKey2中
        Long sdiffstore = jedis.sdiffstore("setKey2",key, key1);


        //交集 -
        Set<String> sinter = jedis.sinter(key, key1);
        System.out.println("交集:"+sinter.toString());

        //获取多个集合的交集，并存放在setKey3中
        jedis.sinterstore("setKey3",key,key1);

        //判断元素是在集合中
        System.out.println("元素是在集合中:"+jedis.sismember(key,"1"));


        //获取集合中的所有元素
        System.out.println("获取集合中的所有元素:"+jedis.smembers(key).toString());


        //移除并返回一个随机元素
        System.out.println("移除并返回一个随机元素"+jedis.spop(key));

        //返回一个随机数
        System.out.println("返回一个随机数："+jedis.srandmember(key));

        //删除元素
        jedis.srem(key,"2");

        //并集
        Set<String> sunion = jedis.sunion(key, key1);
        System.out.println("并集:"+sunion.toString());

        //迭代集合 - 指定size 获取数据
        ScanResult<String> sscan = jedis.sscan(key, jedis.scard(key).toString());
        List<String> result = sscan.getResult();
        System.out.println("迭代集合:"+result.toString());
    }

    /**
     * 有序集合
     */
    public static void sortedSetType() {
        String key = "sortKey";
        String key1 = "sortKey1";

        //单个添加
        jedis.zadd(key,1.0,"v1");
        jedis.zadd(key1,4.0,"v1");
        //批量添加
        Map<String,Double> map = new HashMap<>();
        map.put("v2",2.0);
        map.put("v3",3.0);
        jedis.zadd(key,map);


        //获取集合的Size
        Long zcard = jedis.zcard(key);
        System.out.println("获取集合的Size:"+zcard);

        //指定区间内的 元素size
        Long zcount = jedis.zcount(key, 1.0, 2.0);
        System.out.println("指定1.0~2.0之间的元素Size:" + zcount);

        //增量更新-v1
        jedis.zincrby(key,1.5,"v1");

        //交集（key、key1） - 并存储在另一个key中.
        jedis.zinterstore("sortKey2",key,key1);


        //通过索引区间获取元素
        Set<String> zrange = jedis.zrange(key, 0, 2);
        System.out.println("获取0-2之间的元素："+zrange.toString());

        //分数[1~2]的元素
        Set<String> set1 = jedis.zrangeByScore(key, 1, 2);
        System.out.println("获取分数1-2之间的元素:" + set1.toString());

        //获取元素v1 对应的索引
        Long v1 = jedis.zrank(key, "v1");
        System.out.println("元素v1 对应的索引:"+v1);


        //获取指定索引内的元素。从高到低排序
        Set<String> zrevrange = jedis.zrevrange(key, 0, 5);
        System.out.println("获取0-5索引之间内的元素:"+zrevrange.toString());


        //返回指定得分区间内的元素。从高到低
        Set<String> set2 = jedis.zrevrangeByScore(key, 3, 0);
        System.out.println("获取得分0-3之间的元素:"+set2.toString());

        //获取v3的排名
        Long v3 = jedis.zrevrank(key, "v3");
        System.out.println("获取v3的排名:"+v3);

        //迭代使用

        Long count = jedis.zcard(key);
        ScanResult<Tuple> zscan1 = jedis.zscan(key, "0");
        System.out.println(zscan1.getResult().toString());

        //字典使用
        String dictKey = "dictKey";
        jedis.zadd(dictKey,0,"a");
        jedis.zadd(dictKey,0,"b");
        jedis.zadd(dictKey,0,"c");
        jedis.zadd(dictKey,0,"d");

        Long zlexcount = jedis.zlexcount(dictKey, "a", "a");
        System.out.println("获取字典c-d之间的元素的Size:"+zlexcount);


        Set<String> set = jedis.zrangeByLex(dictKey, "b", "d");
        System.out.println("获取字典b-d之间的元素:"+set.toString());
    }

    /**
     * 其它API使用
     */
    public void otherApi() {
        //批量命令使用
        Pipeline pipelined = jedis.pipelined();
        pipelined.lpush("ke1","1");
        pipelined.zadd("ke1",1.0,"2");
        pipelined.set("key3","1");

        //执行命令
        pipelined.sync();

        //执行命令并返回结果 - 关闭链接
        List<Object> list = pipelined.syncAndReturnAll();
    }

}

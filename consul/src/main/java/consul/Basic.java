package consul;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.StatusClient;
import com.orbitz.consul.cache.ConsulCache;
import com.orbitz.consul.cache.KVCache;
import com.orbitz.consul.model.ConsulResponse;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import com.orbitz.consul.model.health.ServiceHealth;
import com.orbitz.consul.model.kv.Value;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Basic
 * @Author bin
 * @Date 2020/3/30 下午4:29
 * @Decr TODO
 *      Consul Api 使用
 *      https://github.com/rickfast/consul-client
 *      https://github.com/Ecwid/consul-api
 * @Link TODO
 **/
public class Basic {


    @Test
    public void connection(){
        //默认连接本地。端口8500
        Consul client = Consul.builder().build();

        //指定URL连接
        //Consul.builder().withUrl()
        Consul.builder().withHostAndPort(HostAndPort.fromString("192.168.1.246:8500")).build();
    }

    /**
     * 注册服务&健康检查
     */
    @Test
    public void checkService() throws NotRegisteredException{
        Consul client = Consul.builder().build();

        String id = "1";
        ImmutableRegistration service = ImmutableRegistration.builder()
                .id(id)
                .name("myService")
                .port(8080)
                .check(Registration.RegCheck.ttl(3L))
                .tags(Collections.singletonList("tag1"))
                .meta(Collections.singletonMap("version", "1.0"))
                .build();

        //注册服务
        client.agentClient().register(service);

        //检查服务
        //使用时，要在TTL过期前连续检查,否则不可用
        while (true){
            System.out.println("check service");
            client.agentClient().pass(id);
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 查找可用的服务By 服务名称
     */
    @Test
    public void getHealthySer(){
        Consul client = Consul.builder().build();
        String serviceName= "myService";
        List<ServiceHealth> response = client.healthClient().getHealthyServiceInstances(serviceName).getResponse();
        for(ServiceHealth serviceHealth:response){
            System.out.println(serviceHealth.getService().toString());
        }
    }


    /**
     * KV存储
     */
    @Test
    public void storeKVT(){
        KeyValueClient client = Consul.builder().build().keyValueClient();
        client.putValue("key/test/fold","value");
    }
    @Test
    public void storeKV(){
        KeyValueClient client = Consul.builder().build().keyValueClient();

        //优先-注册监听器
        KVCache newCache = KVCache.newCache(client, "key");
        Listener listener = new Listener();
        newCache.addListener(listener);
        newCache.start();

        //在进行数据插入
        client.putValue("key/test/fold","value");

        while (true){
            System.out.println(String.format("变化次数: %s",listener.getCallCount()));
            System.out.println(String.format("values : %s",listener.getLastValues().get("key/test/fold").getValueAsString().get()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Listener implements ConsulCache.Listener<String,Value> {
        private int callCount = 0;
        private Map<String, Value> lastValues;

        @Override
        public void notify(Map<String, Value> newValues) {
                callCount ++;
                lastValues = newValues;
        }
        public int getCallCount() {
            return callCount;
        }

        public Map<String, Value> getLastValues() {
            return lastValues;
        }
    }

    @Test
    public void getPeer(){
        StatusClient statusClient = Consul.builder().build().statusClient();
        System.out.println(statusClient.getLeader());

    }
}

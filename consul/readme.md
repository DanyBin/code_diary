
# Consul 调研&使用
```
    官网: https://www.consul.io/docs/install/index.html
```
## 按照Consul-采用二进制按照

* 下载地址: https://www.consul.io/downloads.html

* 配置文件如下
```    
{
  "datacenter": "consul-1",
  "data_dir": "/opt/software/consul-1.7.2/data",
  "log_file":"/opt/software/consul-1.7.2/log/",
  "log_rotate_max_files":5,
  "log_level": "INFO",
  "server": true,
  "ui":true,
  "addresses":{
    "https":"0.0.0.0"
  },
  "ports": {
    "https":8501
  }
}
```

* 启动命令 consul agent -bootstrap -config-dir conf.json >/dev/null 2>&1 &

* 端口释义 https://www.consul.io/docs/install/ports.html

* 架构设计 https://www.consul.io/docs/internals/architecture.html

* API  https://www.consul.io/api/index.html


## 一、集群命令
##### 1 加入集群
```
 $ consul join <address> // 例如：consul join 127.0.01
 $ curl localhost:8500/v1/agent/join/<ip>
```

##### 2 离开集群
```
 $ consul leave //当前节点上执行
 $ curl localhost:8500/v1/agent/leave
```

##### 3 查看集群成员（所有）
```
 $ consul members //当前节点上执行
 $ curl localhost:8500/v1/agent/members
```

##### 4 查看集群成员（从节点）
```
 $ curl localhost:8500/v1/status/peers
```

##### 5 查看集群成员（leader）
```
 $ curl localhost:8500/v1/status/leader
```
##### 6 查看集群服务（所有）
```
 $ curl localhost:8500/v1/catalog/services
```
##### 7 查看集群服务（节点）
```
 $ curl localhost:8500/v1/catalog/node/localhost
```
##### 8 查看集群服务对应的节点
```
 $ curl localhost:8500/v1/catalog/service/consul
```

## 二、KV存储 

##### 1 PUT
```
 $ consul kv put foo/bar 20
 $ curl -X PUT -d '30' http://localhost:8500/v1/kv/foo/bar
```
##### 2 GET
```
consul kv get -recurse -detailed // 获取数据及元数据
curl http://localhost:8500/v1/kv/\?recurse

获取value
consul kv get zhaolong/test
consul kv get -detailed ttt/test
curl http://localhost:8500/v1/kv/ttt/test


consul kv get -recurse key // 获取以key开头的所有
consul kv get -keys  -separator="" test //获取以test为开头的key
curl http://localhost:8500/v1/kv/test\?keys


获取key
 consul kv get -keys -separator=""
 curl http://localhost:8500/v1/kv/?keys
```

##### 3 DELETE
```
# 删除指定的key
$ consul kv delete ttt/test 
$ curl -X DELETE http://localhost:8500/v1/kv/ttt/test
 
# 递归删除所有以zhaolong开头的key
$ consul kv delete -recurse ttt
$ curl -X DELETE http://localhost:8500/v1/kv/ttt?recurse
```

## 三、健康检查
Consul的一个基本功能是提供系统级和应用级健康检查。
如果健康检查与某个服务关联，则称为是应用级的；如果不予服务关联，则监控整个节点的健康。

check定义在配置文件中，或运行时通过HTTP接口添加。Check是通过HTTP与节点保持一致。
https://www.consul.io/docs/agent/checks.html
```
# 查看节点状态
curl http://localhost:8500/v1/health/node/localhost 

# 查看节点与服务状态
curl http://localhost:8500/v1/health/service/myService

#查看服务状态
curl http://localhost:8500/v1/health/checks/myService

# 查询符合状态（any, passing, warning, or critical）的服务
curl http://localhost:8500/v1/health/state/critical
```

## 四、服务发现
Consul Agent提供简单的Service定义格式用于申报服务的可用性，并与健康检查相关联。
如果健康检查与服务关联，则认为服务是应用级的。服务可以定义在配置文件中或在运行时通过HTTP接口添加
https://www.consul.io/docs/agent/services.html
https://blog.csdn.net/younger_china/article/details/52243788

## 五、监控
https://www.consul.io/docs/agent/watches.html
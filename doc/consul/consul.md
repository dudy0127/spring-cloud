### SpringCloud consul 服务注册和发现

#### 1.consul 
https://www.consul.io/

Consul is a distributed service mesh to connect, secure, and configure services across any runtime platform and public or private cloud

Consul是一个分布式服务网格，用于跨任何运行时平台和公共云或私有云连接，保护和配置服务


##### 1.1 install & command
https://www.consul.io/intro/getting-started/install.html

##### 1.2 web 
安全启动后默认访问地址: http://localhost:8500/


##### 2. Docker compose 

docker dev 模式启动:

$ docker run -d --name=dev-consul -p 8500:8500 -e CONSUL_BIND_INTERFACE=eth0 consul



https://hub.docker.com/r/library/consul/
https://www.jianshu.com/p/f8746b81d65d
https://github.com/emdem/consul-cluster-compose
https://github.com/hashicorp/consul/blob/master/demo/docker-compose-cluster/docker-compose.yml 官方提供集群DEMO

##### 2.1 构建启动
docker-compose up  启动
docker-compose stop 

-d  后台运行

--no-recreate 不重新创建只启动未启动的容器




##### refer to

http://www.ymq.io/2017/11/26/spring-cloud-consul/



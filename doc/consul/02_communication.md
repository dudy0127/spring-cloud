# Spring Boot 2.0.4&Spring Cloud F版本(二) 服务间通信及Feign和Hystrix使用

单机程序中，可以通过语言级方法或函数实现调用。而在微服务中，应用程序是部署在不用机器上的，每个服务实例都是一个进程。因此服务之间的通信必须是进程间的通信(IPC)。

## 交互风格

客户端和服务端交互方式可以在两个维度考虑。

一个是一对一，一个是一对多

一个是同步/异步。

|          | **一到一**    | 一个一对多    |
| -------- | ------------- | ------------- |
| **同步** | 请求/响应     | -             |
| **异步** | 通知          | 发布/订阅     |
|          | 请求/异步响应 | 发布/异步响应 |

基于微服务的应用程序，有的服务单个IPC就够了，而有的可能需要多个组合。

### 同步

两种比较流行的同步通信协议是REST和RPC。Spring Cloud Feign通信使用为REST。

比较流行的RPC框架:grpc ,Dubbo,Thrift。

### 异步

异步要求客户端请求并不实时响应。

当一个请求过来，可能需要调用多个服务，每个服务响应时间都比较长，如果做成同步，可能响应时间就过长了。

这时候可能就要引入消息中间件了。

https://www.nginx.com/blog/building-microservices-inter-process-communication/	 更多信息

### 容错

在分布式系统中，存在部分失败的风险，由于客户端和服务端是独立部署的，因此服务可能无法及时响应客户端的请求。由于故障或维护，服务可能会停机。或者服务可能会超载并且对请求的响应速度非常慢。

[Netflix Hystrix](https://github.com/Netflix/Hystrix) 提供了一些策略模式，包括

> 1.超时
>
> 2.断路器
>
> 3.提供回退
>
> 4.限制未完成请求的数量



### feign 声明式调用 

Feign makes writing java http clients easier

内置了负载均衡，封装了Ribbon 

默认使用的是HttpURLCollection实现网络请求的，还可以使用HTTPClient和OKhttp

pom.xml   SB2.0 后改为 openfeign
```xml
<!--feign-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

```

启动类application.java 中添加注解: **@EnableFeignClients** 开启FeignClient功能

写一个Client:  
**@FeignClient("consul-server")**

```java
@FeignClient("consul-server")
public interface RemoteService {

    @RequestMapping(value = "api",method = RequestMethod.GET) 
    String api(@RequestParam("api")String api);
}

```
启动consul-server,consul-server01 ,调用 curl http://localhost:8503/api/ 会轮训调用从server和server01


#### TODO
负载均衡是怎么实现的？

### Hystrix 熔断器

分布式系统中服务与服务之间依赖错综复杂，当某个服务出现故障时，会导致调用其服务的其他服务出现远程调度的线程阻塞。
**这也意味着你要把处理错误的代码当成正常功能的代码来处理**

Hystrix 是Netflix公司是通过隔离服务的访问点阻止联动故障的，并提供了故障解决方案，从而提高了整个分布式系统的弹性。

Hystrix 
1. 提供快速失败机制
2. 提供回退 fallback 方案
3. 使用熔断机制，防止故障扩散到其他服务
4. 监控组件 Hystrix Dashboard ，可以使用Turbine聚合监控


SpringCloud Feign中Hystrix的使用
在 consul-client上 pom.xml 中添加
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```

application.yml
```yaml
feign:
  hystrix:
    enabled: true # 开启Feign熔断
```

修改Feign RemoteService接口,添加fallback 快速失败回调类

@FeignClient(value = "consul-server",fallback = HystrixClientFallback.class)

```java

@Component
public class HystrixClientFallback implements RemoteService{

    @Override
    public String api(String api) {
        return "Hystrix fallback ...";
    }
}
```

启动 consul-server,consul-client ,访问:http://localhost:8053/api/ 

停掉 consul-server 在访问 得到   Hystrix fallback ... 


####  使用tuibine 聚合监控

Hystrix Dashboard 监控服务的熔断器情况时，最多只有一个Hystrix Dashboard 主页，服务数量很多时，很不方便。

tuibine 可以监控多个Hystrix Dashboard主页，集中进行监控

我们使用的是consul作为注册中心，而tuibine默认是使用eureka作为注册中心，不把eureka排除的话会报
Field registration in o.s.c.c.s.ServiceRegistryAutoConfiguration$ServiceRegistryEndpointConfiguration required a single bean, but 2 were found:

类似于这个https://github.com/spring-cloud/spring-cloud-netflix/issues/1973**


pom.xml 

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-turbine</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
```

Application.java 启动类 添加注解
```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableTurbine
@EnableHystrix
public class HystrixDashboardTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardTurbineApplication.class, args);
	}
}

```

application.yml
```yaml
server:
  port: 8504
spring:
  cloud:
    consul:
      discovery:
        health-check-path: /actuator/health
        health-check-interval: 10s
        instance-id: ${spring.application.name}:${vcap.application.instance_id:${spring.application.instance_id:${random.value}}}
        service-name: ${spring.application.name}
      host: localhost
      port: 8500

  application:
    name: hystrix-turbine
#  boot:
#    admin:
#      client:
#        url: http://localhost:8700

# spring admin
#management:
#  endpoints:
#    web:
#      exposure:
#        include: "*"
#  endpoint:
#      health:
#        show-details: ALWAYS

turbine:
  aggregator:
    cluster-config: default     #需要监控的服务集群名
  app-config: consul-client    #需要监控的服务名
  cluster-name-expression: new String('default')
#  instanceUrlSuffix:
#      default: actuator/hystrix.stream    #key是clusterConfig集群的名字，value是hystrix监控的后缀，springboot2.0为actuator/hystrix.stream
```

启动后访问:

http://localhost:8504/hystrix
可以看到
![hystrix](../img/hystrix.png)

填写
http://localhost:8504/turbine.stream ，monitor，开始进去可能没有数据

http://localhost:8504/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8504%2Fturbine.stream%20


只启动两个 client 端

java -jar target/consul-client-0.0.1-SNAPSHOT.jar --server.port=8503

java -jar target/consul-client-0.0.1-SNAPSHOT.jar --server.port=8505

访问http://localhost:8505/api/ 或者 http://localhost:8503/api/  都行，多访问几次

就会得到,其中右下角4代表失败次数，Hosts=2代表集群有2个应用
![turbine](../img/turbine.png)
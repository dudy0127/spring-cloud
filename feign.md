### feign 声明式调用 

Feign makes writing java http clients easier

内置了负载均衡，封装了Ribbon 

默认使用的是HttpURLCollection实现网络请求的，还可以使用HTTPClient和OKhttp


pom.xml 
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

    @RequestMapping(value = "api",method = RequestMethod.GET) // 貌似不能使用 @GetMaping这种注解，method也需要写
    String api(@RequestParam("api")String api);
}

```
启动consul-server,consul-server01 ,调用 curl http://localhost:8503/api/ 会轮训调用从server和server01


#### TODO
负载均衡是怎么实现的？

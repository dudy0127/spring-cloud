package io.github.dudy.springcloud.consulclient;

import io.github.dudy.springcloud.consulclient.remote.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
@EnableHystrix
public class ConsulClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulClientApplication.class, args);
	}

	@Autowired
	private RemoteService remoteService;

	@Autowired
	private LoadBalancerClient loadBalancer;

	@Autowired
	private DiscoveryClient discoveryClient;

	/**
	 * 从所有服务中选择一个服务（轮询）
	 */
	@RequestMapping("/discover")
	public Object discover() {
		return loadBalancer.choose("consul-server").getUri().toString();
	}

	/**
	 * 获取所有服务
	 */
	@RequestMapping("/services")
	public Object services() {
		return discoveryClient.getServices();
	}

	@RequestMapping("/home")
	public String home() {
		return "hi ,i'm consul client";
	}

	@RequestMapping("/api")
	public String api(){
		return  remoteService.api("test feign");
	}
}

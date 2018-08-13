package io.github.dudy.springcloud.consulserver01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulServer01Application {

	@RequestMapping("/home")
	public String home() {
		return "hi ,i'm consul server01";
	}

	@RequestMapping("/api")
	public String api(@RequestParam("api")String api){
		return "server01 api test:" + api;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsulServer01Application.class, args);
	}
}

package io.github.dudy.springcloud.consulserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulServerApplication {

	@RequestMapping("/home")
	public String home() {
		return "hi ,i'm consul server";
	}


	@RequestMapping(value = "/api")
	public String api(@RequestParam("api")String api){
		return "server api test:" + api;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsulServerApplication.class, args);
	}
}

package io.github.dudy.consul.config.consulconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableAutoConfiguration
public class ConsulConfigApplication {


	@Value("${foo}")
	private String foo;

	@GetMapping("/foo")
	public String getFoo() {
		return foo;
	}


	public static void main(String[] args) {
		SpringApplication.run(ConsulConfigApplication.class, args);
	}
}

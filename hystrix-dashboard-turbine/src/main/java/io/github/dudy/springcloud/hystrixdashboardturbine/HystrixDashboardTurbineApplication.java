package io.github.dudy.springcloud.hystrixdashboardturbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

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

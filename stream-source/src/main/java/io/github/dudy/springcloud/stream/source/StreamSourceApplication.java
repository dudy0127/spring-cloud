package io.github.dudy.springcloud.stream.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StreamSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamSourceApplication.class, args);
    }

}

package io.github.dudy.springcloud.consulclient.remote;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "consul-server",fallback = HystrixClientFallback.class)
public interface RemoteService {

    @GetMapping(value = "api")
    String api(@RequestParam("api")String api);
}

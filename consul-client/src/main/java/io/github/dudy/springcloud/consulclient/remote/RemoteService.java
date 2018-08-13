package io.github.dudy.springcloud.consulclient.remote;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("consul-server")
public interface RemoteService {

    @RequestMapping(value = "api",method = RequestMethod.GET)
    String api(@RequestParam("api")String api);
}

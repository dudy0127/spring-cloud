package io.github.dudy.springcloud.consulclient.remote;


import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallback implements RemoteService{

    @Override
    public String api(String api) {
        return "Hystrix fallback ...";
    }
}

package io.github.dudy.springcloud.stream.source.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class RestTestController {

    @RequestMapping("")
    public Object test(){
        return "test";
    }

}

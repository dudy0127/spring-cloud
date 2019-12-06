package io.github.com.dudy.springcloud.streamsink.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface GreetingsStreams {
    String INPUT = "goods-in";

    @Input(INPUT)
    SubscribableChannel inboundGreetings();

}

package io.github.dudy.springcloud.stream.source.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface GreetingsStreams {
    String OUTPUT = "goods-out";

    @Output(OUTPUT)
    MessageChannel outboundGreetings();
}

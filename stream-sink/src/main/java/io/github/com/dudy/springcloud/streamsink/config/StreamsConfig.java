package io.github.com.dudy.springcloud.streamsink.config;

import io.github.com.dudy.springcloud.streamsink.stream.GreetingsStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(GreetingsStreams.class)
public class StreamsConfig {
}

package io.github.dudy.springcloud.stream.source.config;

import io.github.dudy.springcloud.stream.source.stream.GreetingsStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(GreetingsStreams.class)
public class StreamsConfig {
}

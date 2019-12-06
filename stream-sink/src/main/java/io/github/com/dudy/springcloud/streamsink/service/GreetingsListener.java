package io.github.com.dudy.springcloud.streamsink.service;

import io.github.com.dudy.springcloud.streamsink.model.Greetings;
import io.github.com.dudy.springcloud.streamsink.stream.GreetingsStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GreetingsListener {
    /**
     *
     * @param greetings
     * @param partition  从哪个分区获取的数据
     */
    @StreamListener(GreetingsStreams.INPUT)
    public void handleGreetings(@Payload Greetings greetings,@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("Received message: {},from partition : {}", greetings,partition);
    }
}

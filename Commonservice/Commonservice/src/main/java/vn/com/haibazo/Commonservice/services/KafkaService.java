package vn.com.haibazo.Commonservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    public void sendMessage(String topic, String message){
        kafkaTemplate.send(topic, message);
        log.info("Message sent to topic: {} with value: {}", topic, message);
    }
}

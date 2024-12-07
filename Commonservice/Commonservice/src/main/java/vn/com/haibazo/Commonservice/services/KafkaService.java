package vn.com.haibazo.Commonservice.services;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;  // kafkaTemplate is injected by Spring Boot automatically from KafkaAutoConfiguration.java

    @Autowired
    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message){
        kafkaTemplate.send(topic, message);
        log.info("Message sent to topic: {} with value: {}", topic, message);
    }
//    public void sendOrder(String topic, Object create){
//        kafkaTemplate.send(topic,create);
//        log.info("Message sent to topic: {} with value: {}", topic, create);
//    }
}

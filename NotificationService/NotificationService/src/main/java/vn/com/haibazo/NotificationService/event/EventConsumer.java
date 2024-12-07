package vn.com.haibazo.NotificationService.event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.RetriableException;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import vn.com.haibazo.NotificationService.model.MessageCommandRequest;

@Component
@Slf4j
public class EventConsumer {

    @RetryableTopic(
            attempts = "2", // so lan retry 3 lan + 1 lan DLQ
            backoff = @Backoff(delay = 1000 , multiplier = 2), // lan 1 la 1s -> lan 2 la 2s -> lan 3 la 4 s
            autoCreateTopics = "true",
            include = {
                    // danh sách các lỗi khi để có thể cho phép retries lại
                    RuntimeException.class,
                    RetriableException.class
            },
            dltStrategy = DltStrategy.FAIL_ON_ERROR
    )
    @KafkaListener(topics = "test", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message) throws JsonProcessingException {
        System.out.println("received message: " + message.toUpperCase());
        // convert string -> object
        ObjectMapper objectMapper = new ObjectMapper();
        MessageCommandRequest messageCommandRequest = objectMapper.readValue(message, MessageCommandRequest.class);
        // convert object -> string
        String convertedToString = String.valueOf(messageCommandRequest);
        System.out.println(convertedToString.toUpperCase());
        // throw new RuntimeException("Received message: " + "error");
    }
    // sau khi da dua vao dltQUeue thi se xu ly cai gi ?
    @DltHandler
    public void processDltMessage(@Payload String message){
        System.out.println("Dead letter queue receive message");
    }
}

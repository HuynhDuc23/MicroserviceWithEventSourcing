//package vn.com.haibazo.NotificationService.event;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.event.EventListener;
//import org.springframework.kafka.annotation.DltHandler;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.RetryableTopic;
//import org.springframework.kafka.retrytopic.DltStrategy;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.retry.annotation.Backoff;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class CreateBookEvent {
//    @RetryableTopic(
//            attempts = "4",
//            backoff = @Backoff(delay = 5000, multiplier = 2) ,
//            autoCreateTopics = "true",
//            include = {
//                    Exception.class
//            },
//            dltStrategy = DltStrategy.FAIL_ON_ERROR
//    )
//    @KafkaListener(topics = "create",containerFactory = "kafkaListenerContainerFactory")
//    public void listen(Object create){
//        log.info("Received create book event: " + create.toString());
//        // Process the create book event
//    }
//    @DltHandler
//    public void handleDlt(@Payload  String payload) {
//        log.info("Received DLT message: " + payload);
//    }
//}

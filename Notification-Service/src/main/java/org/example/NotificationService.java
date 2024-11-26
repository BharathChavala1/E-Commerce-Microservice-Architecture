package org.example;


import lombok.extern.slf4j.Slf4j;
import org.example.notificationDTO.OrderPlacedEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class NotificationService {
    public static void main(String[] args) {
        SpringApplication.run(NotificationService.class,args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent){
        log.info("Received Notification form Order: "+orderPlacedEvent.getOrderNumber());

    }
}
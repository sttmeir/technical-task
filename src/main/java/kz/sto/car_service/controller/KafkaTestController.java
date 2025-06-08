package kz.sto.car_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import kz.sto.car_service.enums.RequestStatus;
import kz.sto.car_service.kafka.message.KafkaRequestEvent;
import kz.sto.car_service.kafka.message.RequestCreateMsg;
import kz.sto.car_service.kafka.message.RequestEventType;
import kz.sto.car_service.kafka.message.RequestStatusChangeMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Контроллер для проверки Kafka")
@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaTestController {

    private final KafkaTemplate<String, KafkaRequestEvent> kafkaTemplate;

    @Value("${spring.kafka.consumer.request.topic}")
    private String topic;

    @PostMapping("/test-create")
    public ResponseEntity<String> sendCreateEvent(@RequestParam Long clientId,
                                                  @RequestParam String description) {
        RequestCreateMsg createMsg = new RequestCreateMsg(clientId, description);
        KafkaRequestEvent event = new KafkaRequestEvent(RequestEventType.CREATE, createMsg, null);
        kafkaTemplate.send(topic, event);
        return ResponseEntity.ok("Kafka CREATE событие отправлено");
    }

    @PostMapping("/test-update")
    public ResponseEntity<String> sendStatusUpdate(
            @RequestParam Long requestId,
            @RequestParam RequestStatus newStatus,
            @RequestParam String changedBy,
            @RequestParam String reason) {

        RequestStatusChangeMsg updateMsg = new RequestStatusChangeMsg(requestId, newStatus, changedBy, reason);
        KafkaRequestEvent event = new KafkaRequestEvent(RequestEventType.UPDATE, null, updateMsg);
        kafkaTemplate.send(topic, event);
        return ResponseEntity.ok("Kafka UPDATE событие отправлено");
    }
}

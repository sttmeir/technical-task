package kz.sto.car_service.kafka.listener;

import kz.sto.car_service.kafka.message.KafkaRequestEvent;
import kz.sto.car_service.kafka.message.RequestCreateMsg;
import kz.sto.car_service.kafka.message.RequestEventType;
import kz.sto.car_service.kafka.message.RequestStatusChangeMsg;
import kz.sto.car_service.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaRequestEventListener {

    private final RequestService requestService;

    @KafkaListener(
            topics = "${kafka.consumer.request.topic}",
            groupId = "${kafka.consumer.request.group}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleKafkaEvent(KafkaRequestEvent event) {
        log.info("Получено Kafka событие: {}", event);

        if (event.requestEventType() == RequestEventType.CREATE) {
            handleCreate(event.requestCreateMsg());
        } else if (event.requestEventType() == RequestEventType.UPDATE) {
            handleUpdate(event.requestStatusChangeMsg());
        } else {
            log.warn("Неизвестный тип события: {}", event.requestEventType());
        }
    }

    private void handleCreate(RequestCreateMsg msg) {
        try {
            log.info("Создание заявки от клиента ID={} с описанием: {}", msg.clientId(), msg.description());
            requestService.createRequest(msg.clientId(), msg.description());
        } catch (Exception e) {
            log.error("Ошибка при создании заявки через Kafka: {}", e.getMessage(), e);
        }
    }

    private void handleUpdate(RequestStatusChangeMsg msg) {
        try {
            log.info("Изменение статуса заявки ID={} на {}, изменил: {}, причина: {}",
                    msg.requestId(), msg.newStatus(), msg.changedBy(), msg.reason());
            requestService.changeStatus(msg.requestId(), msg.newStatus(), msg.changedBy(), msg.reason());
        } catch (Exception e) {
            log.error("Ошибка при обновлении статуса через Kafka: {}", e.getMessage(), e);
        }
    }
}

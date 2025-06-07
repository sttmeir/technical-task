package kz.sto.car_service.kafka.message;

public record KafkaRequestEvent(
        RequestEventType requestEventType,
        RequestCreateMsg requestCreateMsg,
        RequestStatusChangeMsg requestStatusChangeMsg
) {
}

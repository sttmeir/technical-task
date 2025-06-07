package kz.sto.car_service.kafka.message;

public record RequestCreateMsg(
        Long clientId,
        String description
) {
}

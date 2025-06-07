package kz.sto.car_service.kafka.message;

import kz.sto.car_service.enums.RequestStatus;

public record RequestStatusChangeMsg(
        Long requestId,
        RequestStatus newStatus,
        String changedBy,
        String reason
) {
}

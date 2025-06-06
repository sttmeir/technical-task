package kz.sto.car_service.service;

import kz.sto.car_service.domain.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {
    public void notifyClient(Client client, String message) {
        log.info("[NOTIFY] To: {} ({}) | Message: {}", client.getName(), client.getPhoneNumber(), message);
    }
}

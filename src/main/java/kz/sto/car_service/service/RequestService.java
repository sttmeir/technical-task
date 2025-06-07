package kz.sto.car_service.service;

import kz.sto.car_service.domain.Client;
import kz.sto.car_service.domain.Request;
import kz.sto.car_service.domain.StatusChangeHistory;
import kz.sto.car_service.enums.RequestStatus;
import kz.sto.car_service.repository.ClientRepository;
import kz.sto.car_service.repository.RequestRepository;
import kz.sto.car_service.repository.StatusChangeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static kz.sto.car_service.enums.RequestStatus.RECEIVED;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepo;
    private final ClientRepository clientRepo;
    private final StatusChangeHistoryRepository historyRepo;
    private final NotificationService notificationService;

    public void createRequest(Long clientId, String description) {
        Client client = clientRepo.findById(clientId).orElseThrow();
        Request request = new Request(null, client, description, RECEIVED,
                LocalDateTime.now(), LocalDateTime.now(), null);
        requestRepo.save(request);
    }

    public List<Request> getByClient(Long clientId) {
        return requestRepo.findByClientId(clientId);
    }

    public List<Request> getByStatus(RequestStatus status) {
        return requestRepo.findByStatus(status);
    }

    public List<Request> getAllRequests() {
        return requestRepo.findAll();
    }

    @Transactional
    public void changeStatus(Long requestId, RequestStatus newStatus, String changedBy, String reason) {
        Request request = requestRepo.findById(requestId).orElseThrow();
        request.setStatus(newStatus);
        request.setUpdatedAt(LocalDateTime.now());
        requestRepo.save(request);

        StatusChangeHistory history = new StatusChangeHistory(null, request, newStatus,
                changedBy, reason, LocalDateTime.now());
        historyRepo.save(history);

        request.getStatusHistory().add(history);
        history.setRequest(request);

        if (newStatus == RequestStatus.DONE) {
            notificationService.notifyClient(request.getClient(), "Ваш автомобиль готов. Спасибо за обращение в СТО!");
        }
    }
}

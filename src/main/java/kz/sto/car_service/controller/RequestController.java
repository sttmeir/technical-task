package kz.sto.car_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import kz.sto.car_service.domain.Request;
import kz.sto.car_service.enums.RequestStatus;
import kz.sto.car_service.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Заявки")
@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @GetMapping("/client/{clientId}")
    public List<Request> getRequestsByClient(@PathVariable Long clientId) {
        return requestService.getByClient(clientId);
    }

    @GetMapping("/status/{status}")
    public List<Request> getRequestsByStatus(@PathVariable RequestStatus status) {
        return requestService.getByStatus(status);
    }

    @GetMapping
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }
}

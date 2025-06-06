package kz.sto.car_service.controller;

import kz.sto.car_service.domain.Request;
import kz.sto.car_service.enums.RequestStatus;
import kz.sto.car_service.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/create")
    public void create(@RequestParam Long clientId, @RequestParam String description) {
        requestService.createRequest(clientId, description);
    }

    @GetMapping("/client/{clientId}")
    public List<Request> getRequestsByClient(@PathVariable Long clientId) {
        return requestService.getByClient(clientId);
    }

    @GetMapping("/status/{status}")
    public List<Request> getRequestsByStatus(@PathVariable RequestStatus status) {
        return requestService.getByStatus(status);
    }
}

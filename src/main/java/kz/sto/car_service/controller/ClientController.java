package kz.sto.car_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import kz.sto.car_service.domain.Client;
import kz.sto.car_service.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Клиенты")
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping("/create")
    public Client create(@RequestParam String name,
                         @RequestParam String phoneNumber) {
        return clientService.save(new Client(null, name, phoneNumber));
    }
}

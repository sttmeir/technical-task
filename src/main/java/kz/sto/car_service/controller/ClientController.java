package kz.sto.car_service.controller;

import kz.sto.car_service.domain.Client;
import kz.sto.car_service.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepository clientRepository;

    @PostMapping("/create")
    public Client create(@RequestParam String name,
                         @RequestParam String phoneNumber) {
        return clientRepository.save(new Client(null, name, phoneNumber));
    }
}

package kz.sto.car_service.service;

import kz.sto.car_service.domain.Client;
import kz.sto.car_service.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client save(Client client) {
        clientRepository.save(client);
        return client;
    }
}

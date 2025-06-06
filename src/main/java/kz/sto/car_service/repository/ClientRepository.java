package kz.sto.car_service.repository;

import kz.sto.car_service.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

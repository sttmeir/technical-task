package kz.sto.car_service.repository;

import kz.sto.car_service.domain.Request;
import kz.sto.car_service.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByClientId(Long clientId);
    List<Request> findByStatus(RequestStatus status);
}

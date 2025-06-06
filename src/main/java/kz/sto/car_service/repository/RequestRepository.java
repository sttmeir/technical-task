package kz.sto.car_service.repository;

import kz.sto.car_service.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}

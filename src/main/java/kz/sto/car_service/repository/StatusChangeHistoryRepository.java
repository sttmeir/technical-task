package kz.sto.car_service.repository;

import kz.sto.car_service.domain.StatusChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusChangeHistoryRepository extends JpaRepository<StatusChangeHistory, Long> {
}

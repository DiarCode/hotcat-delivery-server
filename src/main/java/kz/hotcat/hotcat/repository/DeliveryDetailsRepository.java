package kz.hotcat.hotcat.repository;

import kz.hotcat.hotcat.entity.DeliveryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDetailsRepository extends JpaRepository<DeliveryDetails, Long> {
}

package kz.hotcat.hotcat.repository;

import kz.hotcat.hotcat.entity.DeliveryProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryProviderRepository extends JpaRepository<DeliveryProvider, Long> {
}

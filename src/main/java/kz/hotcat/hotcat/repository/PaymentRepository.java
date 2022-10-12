package kz.hotcat.hotcat.repository;

import kz.hotcat.hotcat.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = "SELECT COUNT(*) FROM payments p WHERE p.timestamp >= date_trunc('month', CURRENT_DATE)" ,nativeQuery = true)
    Long getTotalAmountOfTransactionsInPresentMonth();
}

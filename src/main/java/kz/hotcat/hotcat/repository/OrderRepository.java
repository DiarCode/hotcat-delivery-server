package kz.hotcat.hotcat.repository;

import kz.hotcat.hotcat.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT * FROM orders o ORDER BY o.order_date DESC LIMIT 10", nativeQuery = true)
    List<Order> findAllRecenetOrders();

    @Query(value = "SELECT * FROM orders o WHERE o.app_user_user_id = ?1 ORDER BY o.order_date DESC LIMIT 10", nativeQuery = true)
    List<Order> findAllRecenetOrdersByUserId(Long userId);
}

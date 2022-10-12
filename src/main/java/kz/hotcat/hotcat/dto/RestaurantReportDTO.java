package kz.hotcat.hotcat.dto;

import kz.hotcat.hotcat.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantReportDTO {
    private Long customersAmount;
    private Long transactionsAmount;
    private double monthlyEarning;
    private List<Order> lastOrders;
}

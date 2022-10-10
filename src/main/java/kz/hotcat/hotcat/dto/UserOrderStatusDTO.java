package kz.hotcat.hotcat.dto;

import kz.hotcat.hotcat.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserOrderStatusDTO {
    private Order order;
    private boolean isActive;
}

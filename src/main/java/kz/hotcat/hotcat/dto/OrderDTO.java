package kz.hotcat.hotcat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Long restaurantId;
    private Long deliveryProviderId;
    private Long userId;
    private List<OrderItemDTO> orderItemsList;
}

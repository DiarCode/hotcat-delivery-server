package kz.hotcat.hotcat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long restaurantId;
    private Long deliveryProviderId;
    private Long userId;
    private List<OrderItemDTO> orderItemsList;
}

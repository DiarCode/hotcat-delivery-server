package kz.hotcat.hotcat.dto;

import kz.hotcat.hotcat.entity.DeliveryDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderDetailsDTO {
    private PaymentDTO payment;
    private DeliveryDetails deliveryDetails;
}

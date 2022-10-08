package kz.hotcat.hotcat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PaymentDTO {
    private double totalPrice;
    private Long userId;
    private Long restaurantId;
    private Long paymentMethodId;
}

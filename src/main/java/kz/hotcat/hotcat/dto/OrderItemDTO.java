package kz.hotcat.hotcat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Long foodId;
    private int count;
    private List<Long> toppings;
}

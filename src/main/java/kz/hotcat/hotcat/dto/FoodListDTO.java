package kz.hotcat.hotcat.dto;

import kz.hotcat.hotcat.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodListDTO {
    private List<Food> foodList;
}

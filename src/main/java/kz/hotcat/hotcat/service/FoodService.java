package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.entity.Food;
import kz.hotcat.hotcat.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public List<Food> getAllFoods() {
        return  foodRepository.findAll();
    }

    public Food getFoodById(Long foodId) {
        return foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("No such food"));
    }

    public Food createNewFood(Food food) {
        return foodRepository.save(food);
    }

    public void deleteFoodById(Long foodId) {
        boolean isFoodExists = foodRepository.existsById(foodId);

        if(!isFoodExists) {
            throw new RuntimeException("No such food");
        }

        foodRepository.deleteById(foodId);
    }
}

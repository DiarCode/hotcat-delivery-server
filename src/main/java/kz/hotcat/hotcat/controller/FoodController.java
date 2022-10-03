package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.entity.DeliveryProvider;
import kz.hotcat.hotcat.entity.Food;
import kz.hotcat.hotcat.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/foods")
@AllArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{id}")
    public Food getFoodById(@PathVariable(name = "id") Long foodId) {
        return foodService.getFoodById(foodId);
    }

    @PostMapping
    public Food createNewFood(@RequestBody Food food) {
        return foodService.createNewFood(food);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodById(@PathVariable(name = "id") Long foodId) {
        foodService.deleteFoodById(foodId);
    }
}

package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.dto.FoodListDTO;
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

    @PostMapping("/{foodId}/menu/{menuId}")
    public void assignFoodToMenuById(@PathVariable(name = "menuId") Long menuId,
                                     @PathVariable(name = "foodId") Long foodId) {
        foodService.assignFoodToMenuById(menuId, foodId);
    }

    @PostMapping("/single/menu/{menuId}")
    public Food createNewFoodForMenuById(@PathVariable(name = "menuId") Long menuId, @RequestBody Food food) {
        return foodService.createNewFoodForMenuById(menuId, food);
    }

    @PostMapping("/list/menu/{menuId}")
    public void createNewFoodsForMenuById(@PathVariable(name = "menuId") Long menuId, @RequestBody FoodListDTO foodListDTO) {
        foodService.createNewFoodsForMenuById(menuId, foodListDTO);
    }
}

package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.dto.FoodListDTO;
import kz.hotcat.hotcat.entity.Food;
import kz.hotcat.hotcat.entity.Menu;
import kz.hotcat.hotcat.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final MenuService menuService;

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

    @Transactional
    public void assignFoodToMenuById(Long menuId, Long foodId) {
        Food foodCandidate = getFoodById(foodId);
        Menu menuCandidate = menuService.getMenuById(menuId);

        boolean isFoodExistsInMenu = checkIfFoodExistsInMenuById(menuCandidate, foodCandidate);

        if(isFoodExistsInMenu){
            throw new RuntimeException("Such food already exists in this menu");
        }

        foodCandidate.assignFoodToMenu(menuCandidate);
    }

    public boolean checkIfFoodExistsInMenuById(Menu menu, Food food) {
        Menu candidate = menuService.getMenuById(menu.getId());
        return candidate.getFoodList().contains(food);
    }

    @Transactional
    public Food createNewFoodForMenuById(Long menuId, Food food) {
        Food newFood = createNewFood(food);
        assignFoodToMenuById(menuId, newFood.getId());

        return newFood;
    }

    @Transactional
    public void createNewFoodsForMenuById(Long menuId, FoodListDTO foodListDTO) {
        for (Food food : foodListDTO.getFoodList()){
            Food newFood = createNewFood(food);
            assignFoodToMenuById(menuId, newFood.getId());
        }
    }
}

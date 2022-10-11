package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.entity.Menu;
import kz.hotcat.hotcat.entity.Restaurant;
import kz.hotcat.hotcat.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@AllArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable(name = "id") Long restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }

    @GetMapping("/search")
    public List<Restaurant> getAllRestaurantsByName(@RequestParam String name) {
        return restaurantService.getAllRestaurantsByName(name);
    }

    @PostMapping
    public Restaurant createNewRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createNewRestaurant(restaurant);
    }

    @GetMapping("/{id}/menu")
    public Menu createNewRestaurant(@PathVariable(name = "id") Long restaurantId) {
        return restaurantService.getRestaurantMenuById(restaurantId);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurantById(@PathVariable(name = "id") Long restaurantId) {
        restaurantService.deleteRestaurantById(restaurantId);
    }

    @PostMapping("/{restaurantId}/menu/{menuId}")
    public void assignMenuToRestaurantById(@PathVariable(name = "menuId") Long menuId,
                                     @PathVariable(name = "restaurantId") Long restaurantId) {
        restaurantService.assignMenuToRestaurantById(restaurantId, menuId);
    }

    @PostMapping("/{restaurantId}/assign-menu")
    public void assignMenuToRestaurantById(@RequestBody Menu menu,
                                           @PathVariable(name = "restaurantId") Long restaurantId) {
        restaurantService.assignMenuToRestaurantById(restaurantId, menu);
    }
}

package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.entity.Menu;
import kz.hotcat.hotcat.entity.Restaurant;
import kz.hotcat.hotcat.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuService menuService;

    public List<Restaurant> getAllRestaurants() {
        return  restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("No such restaurant"));
    }

    @Transactional
    public Restaurant createNewRestaurant(Restaurant restaurant) {
        Menu menu = menuService
                .createNewMenu(new Menu(restaurant.getName().trim() + " menu"));

        Restaurant newRestaurant = restaurantRepository.save(restaurant);

        newRestaurant.assignMenuToRestaurant(menu);
        return newRestaurant;
    }

    public Menu getRestaurantMenuById(Long id) {
        Restaurant restaurant = getRestaurantById(id);
        return restaurant.getMenu();
    }

    public void deleteRestaurantById(Long restaurantId) {
        boolean isRestaurantExists = restaurantRepository.existsById(restaurantId);

        if(!isRestaurantExists) {
            throw new RuntimeException("No such restaurant");
        }

        restaurantRepository.deleteById(restaurantId);
    }

    @Transactional
    public void assignMenuToRestaurantById(Long restaurantId, Long menuId) {
        Restaurant restaurantCandidate = getRestaurantById(restaurantId);
        Menu menuCandidate = menuService.getMenuById(menuId);

        restaurantCandidate.assignMenuToRestaurant(menuCandidate);
    }

    @Transactional
    public void assignMenuToRestaurantById(Long restaurantId, Menu menu) {
        Restaurant restaurantCandidate = getRestaurantById(restaurantId);
        restaurantCandidate.assignMenuToRestaurant(menu);
    }

    public List<Restaurant> getAllRestaurantsByName(String name) {
        String trimmedName = name.trim();

        if(trimmedName.isEmpty() || trimmedName.isBlank()) {
            return new ArrayList<>();
        }

        return restaurantRepository.findAllByNameContainsIgnoreCase(trimmedName);
    }
}

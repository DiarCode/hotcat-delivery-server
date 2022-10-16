package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.entity.Menu;
import kz.hotcat.hotcat.entity.Restaurant;
import kz.hotcat.hotcat.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    public List<Menu> getAllMenus() {
        return  menuRepository.findAll();
    }

    public Menu getMenuById(Long menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("No such menu"));
    }

    public Menu createNewMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deleteMenuById(Long menuId) {
        menuRepository.deleteById(menuId);
    }

    public Restaurant getRestaurantByMenuId(Long menuId) {
        Menu menu = getMenuById(menuId);
        return menu.getRestaurant();
    }
}

package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.entity.Menu;
import kz.hotcat.hotcat.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menus")
@AllArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/{id}")
    public Menu getMenuById(@PathVariable(name = "id") Long menuId) {
        return menuService.getMenuById(menuId);
    }

    @PostMapping
    public Menu createNewMenu(@RequestBody Menu menu) {
        return menuService.createNewMenu(menu);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuById(@PathVariable(name = "id") Long menuId) {
        menuService.deleteMenuById(menuId);
    }
}

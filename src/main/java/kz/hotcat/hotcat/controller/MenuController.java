package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/menus")
@AllArgsConstructor
public class MenuController {
    private final MenuService menuService;
}

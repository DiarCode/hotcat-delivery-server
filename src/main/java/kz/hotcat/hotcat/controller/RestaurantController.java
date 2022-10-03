package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurants")
@AllArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
}

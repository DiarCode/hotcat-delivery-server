package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.entity.Topping;
import kz.hotcat.hotcat.service.ToppingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/toppings")
@AllArgsConstructor
public class ToppingController {
    private final ToppingService toppingService;

    @GetMapping
    public List<Topping> getAllToppings() {
        return toppingService.getAllToppings();
    }

    @GetMapping("/{id}")
    public Topping getToppingById(@PathVariable(name = "id") Long toppingId) {
        return toppingService.getToppingById(toppingId);
    }

    @PostMapping
    public Topping createNewTopping(@RequestBody Topping topping) {
        return toppingService.createNewTopping(topping);
    }

    @DeleteMapping("/{id}")
    public void deleteToppingById(@PathVariable(name = "id") Long toppingId) {
        toppingService.deleteToppingById(toppingId);
    }
}

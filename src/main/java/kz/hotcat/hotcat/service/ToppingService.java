package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.entity.Topping;
import kz.hotcat.hotcat.repository.ToppingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ToppingService {
    private final ToppingRepository toppingRepository;

    public List<Topping> getAllToppings() {
        return toppingRepository.findAll();
    }

    public Topping getToppingById(Long toppingId) {
        return toppingRepository.findById(toppingId)
                .orElseThrow(() -> new RuntimeException("No such topping"));
    }

    public Topping createNewTopping(Topping topping) {
        return toppingRepository.save(topping);
    }

    public void deleteToppingById(Long toppingId) {
        toppingRepository.deleteById(toppingId);
    }
}

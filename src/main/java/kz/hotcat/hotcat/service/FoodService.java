package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
}

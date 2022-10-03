package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
}

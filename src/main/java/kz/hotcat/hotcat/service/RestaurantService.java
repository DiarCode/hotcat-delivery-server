package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.dto.RestaurantReportDTO;
import kz.hotcat.hotcat.entity.Menu;
import kz.hotcat.hotcat.entity.Order;
import kz.hotcat.hotcat.entity.Restaurant;
import kz.hotcat.hotcat.repository.RestaurantRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuService menuService;
    private final AppUserService appUserService;
    private final OrderService orderService;
    private final PaymentService paymentService;

    public RestaurantService(RestaurantRepository restaurantRepository,
                             @Lazy MenuService menuService,
                             @Lazy AppUserService appUserService,
                             @Lazy OrderService orderService,
                             @Lazy PaymentService paymentService) {
        this.restaurantRepository = restaurantRepository;
        this.menuService = menuService;
        this.appUserService = appUserService;
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

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

    public RestaurantReportDTO getReportOfAllRestaurants() {
        Long customersAmount = appUserService.getTotalUsersAmount();
        double monthlyEarning = orderService.getMonthlyTotalRevenue();
        Long transactionsAmount = paymentService.getTotalAmountOfTransactionsInPresentMonth();
        List<Order> lastOrders = orderService.getAllRecentOrders(10);
        int subscribedCustomersAmount = appUserService.getSubscribedUsersAmount();

        return RestaurantReportDTO.builder()
                .customersAmount(customersAmount)
                .monthlyEarning(monthlyEarning)
                .transactionsAmount(transactionsAmount)
                .lastOrders(lastOrders)
                .subscribedCustomersAmount(subscribedCustomersAmount)
                .build();
    }
}

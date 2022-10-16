package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.entity.Restaurant;
import kz.hotcat.hotcat.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RestaurantServiceTest {
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private MenuService menuService;
    @InjectMocks
    private RestaurantService restaurantService;


    private Restaurant restaurant;

    @BeforeEach
    public void setup(){
        restaurant = Restaurant.builder()
                .id(1L)
                .name("name")
                .hasDelivery(true)
                .deliveryTime("deliveryTime")
                .address("address")
                .openHours("openHours")
                .shortFoodDescription("shortFoodDescription")
                .description("description")
                .image("image")
                .rating("5")
                .build();
    }

    @DisplayName("Delete restaurant by id")
    @Test
    void givenRestaurantId_whenDeleteRestaurant_thenNothing() {
        Long restaurantId = restaurant.getId();

        willDoNothing().given(restaurantRepository).deleteById(restaurantId);

        restaurantService.deleteRestaurantById(restaurantId);

        verify(restaurantRepository, times(1)).deleteById(restaurantId);
    }

    @DisplayName("Create new restaurant")
    @Test
    void givenRestaurantObject_whenSaveRestaurant_thenReturnRestaurantObject() {
        given(restaurantRepository.findById(restaurant.getId()))
                .willReturn(Optional.empty());

        given(restaurantRepository.save(restaurant)).willReturn(restaurant);

        Restaurant savedRestaurant = restaurantService.createNewRestaurant(restaurant);

        assertThat(savedRestaurant).isNotNull();
    }

    @DisplayName("Get restaurant by id")
    @Test
    void givenRestaurantId_whenGetRestaurantById_thenReturnRestaurantObject() {
        given(restaurantRepository.findById(1L)).willReturn(Optional.of(restaurant));

        Restaurant savedRestaurant = restaurantService
                .getRestaurantById(restaurant.getId());

        assertThat(savedRestaurant).isNotNull();
    }
}
package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.dto.OrderItemDTO;
import kz.hotcat.hotcat.entity.Food;
import kz.hotcat.hotcat.entity.OrderItem;
import kz.hotcat.hotcat.entity.Topping;
import kz.hotcat.hotcat.repository.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final FoodService foodService;
    private final ToppingService toppingService;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("No such order item"));
    }

    @Transactional
    public OrderItem createNewOrderItem(OrderItemDTO orderItemDTO) {
        Food food = foodService.getFoodById(orderItemDTO.getFoodId());

        OrderItem candidate = orderItemRepository.save(new OrderItem());
        candidate.setCount(orderItemDTO.getCount());
        candidate.setFood(food);
        candidate.setTotalPrice(orderItemDTO.getCount() * food.getPrice());

        for (Long toppingId: orderItemDTO.getToppings()){
            Topping topping = toppingService.getToppingById(toppingId);
            topping.setOrderItem(candidate);
            candidate.getToppings().add(topping);
        }

        return candidate;
    }

    public void deleteOrderItemById(Long orderItemId) {
        boolean isExists = orderItemRepository.existsById(orderItemId);

        if(!isExists) {
            throw new RuntimeException("No such order item");
        }

        orderItemRepository.deleteById(orderItemId);
    }
}

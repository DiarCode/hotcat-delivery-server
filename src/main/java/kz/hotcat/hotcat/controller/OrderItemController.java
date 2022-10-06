package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.dto.OrderItemDTO;
import kz.hotcat.hotcat.entity.OrderItem;
import kz.hotcat.hotcat.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-items")
@AllArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable(name = "id") Long orderItemId) {
        return orderItemService.getOrderItemById(orderItemId);
    }

    @PostMapping
    public OrderItem createNewOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        return orderItemService.createNewOrderItem(orderItemDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItemById(@PathVariable(name = "id") Long orderItemId) {
        orderItemService.deleteOrderItemById(orderItemId);
    }
}

package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.dto.OrderDTO;
import kz.hotcat.hotcat.entity.Order;
import kz.hotcat.hotcat.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable(name = "id") Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping
    public Order createNewOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createNewOrder(orderDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable(name = "id") Long orderId) {
        orderService.deleteOrderById(orderId);
    }
}

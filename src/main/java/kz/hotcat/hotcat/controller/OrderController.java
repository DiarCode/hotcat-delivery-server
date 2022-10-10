package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.dto.OrderDTO;
import kz.hotcat.hotcat.dto.OrderDetailsDTO;
import kz.hotcat.hotcat.dto.UserOrderStatusDTO;
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

    @GetMapping("/recent")
    public List<Order> getAllRecentOrders() {
        return orderService.getAllRecentOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable(name = "id") Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping
    public Order createNewOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createNewOrder(orderDTO);
    }

    @PostMapping("/{id}/cooked")
    public Order changeCookingStatusOfOrderById(@PathVariable(name = "id") Long orderId) {
        return orderService.changeCookingStatusOfOrderById(orderId);
    }

    @PostMapping("/{id}/delivered")
    public Order changeDeliveryStatusOfOrderById(@PathVariable(name = "id") Long orderId) {
        return orderService.changeDeliveryStatusOfOrderById(orderId);
    }

    @GetMapping("/active/user/{userId}")
    public UserOrderStatusDTO checkIfOrderIsActiveByUserId(@PathVariable(name = "userId") Long userId) {
        return orderService.checkIfOrderIsActiveByUserId(userId);
    }


    @PostMapping("/{id}/fill")
    public Order fillPaymentAndDeliveryDetails(@PathVariable(name = "id") Long orderId,
                                               @RequestBody OrderDetailsDTO orderDetailsDTO){
        return orderService.fillPaymentAndDeliveryDetails(orderId, orderDetailsDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable(name = "id") Long orderId) {
        orderService.deleteOrderById(orderId);
    }
}

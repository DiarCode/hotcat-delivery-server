package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order-items")
@AllArgsConstructor
public class OrderItemController {
    private final OrderService orderService;
}

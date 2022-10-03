package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.service.DeliveryProviderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/deliveries")
@AllArgsConstructor
public class DeliveryProviderController {
    private final DeliveryProviderService deliveryProviderService;
}

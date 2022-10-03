package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.entity.DeliveryProvider;
import kz.hotcat.hotcat.service.DeliveryProviderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deliveries")
@AllArgsConstructor
public class DeliveryProviderController {
    private final DeliveryProviderService deliveryProviderService;

    @GetMapping
    public List<DeliveryProvider> getAllDeliveryProviders() {
        return deliveryProviderService.getAllDeliveryProviders();
    }

    @GetMapping("/{id}")
    public DeliveryProvider getDeliveryProviderById(@PathVariable(name = "id") Long deliveryProviderId) {
        return deliveryProviderService.getDeliveryProviderById(deliveryProviderId);
    }

    @PostMapping
    public DeliveryProvider createNewDeliveryProvider(@RequestBody DeliveryProvider deliveryProvider) {
        return deliveryProviderService.createNewDeliveryProvider(deliveryProvider);
    }

    @DeleteMapping("/{id}")
    public void deleteDeliveryProviderById(@PathVariable(name = "id") Long deliveryProviderId) {
        deliveryProviderService.deleteDeliveryProviderById(deliveryProviderId);
    }
}

package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.entity.DeliveryDetails;
import kz.hotcat.hotcat.entity.DeliveryProvider;
import kz.hotcat.hotcat.service.DeliveryDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery-details")
@AllArgsConstructor
public class DeliveryDetailsController {
    private final DeliveryDetailsService deliveryDetailsService;

    @GetMapping
    public List<DeliveryDetails> getAllDeliveryDetails() {
        return deliveryDetailsService.getAllDeliveryDetails();
    }

    @GetMapping("/{id}")
    public DeliveryDetails getDeliveryDetailsById(@PathVariable(name = "id") Long deliveryDetailsId) {
        return deliveryDetailsService.getDeliveryDetailsById(deliveryDetailsId);
    }

    @PostMapping
    public DeliveryDetails createNewDeliveryDetails(@RequestBody DeliveryDetails deliveryDetails) {
        return deliveryDetailsService.createNewDeliveryDetails(deliveryDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteDeliveryDetailsById(@PathVariable(name = "id") Long deliveryDetailsId) {
        deliveryDetailsService.deleteDeliveryDetailsById(deliveryDetailsId);
    }
}

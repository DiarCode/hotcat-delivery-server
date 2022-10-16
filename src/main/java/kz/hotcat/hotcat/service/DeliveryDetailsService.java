package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.entity.DeliveryDetails;
import kz.hotcat.hotcat.entity.DeliveryProvider;
import kz.hotcat.hotcat.repository.DeliveryDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryDetailsService {
    private final DeliveryDetailsRepository deliveryDetailsRepository;

    public List<DeliveryDetails> getAllDeliveryDetails() {
        return deliveryDetailsRepository.findAll();
    }

    public DeliveryDetails getDeliveryDetailsById(Long deliveryDetailsId) {
        return deliveryDetailsRepository.findById(deliveryDetailsId)
                .orElseThrow(() -> new RuntimeException("No such delivery details"));
    }

    public DeliveryDetails createNewDeliveryDetails(DeliveryDetails deliveryDetails) {
        return deliveryDetailsRepository.save(deliveryDetails);
    }

    public void deleteDeliveryDetailsById(Long deliveryDetailsId) {
        deliveryDetailsRepository.deleteById(deliveryDetailsId);
    }
}

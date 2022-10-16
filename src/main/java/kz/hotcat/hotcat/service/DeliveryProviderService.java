package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.entity.DeliveryProvider;
import kz.hotcat.hotcat.repository.DeliveryProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryProviderService {
    private final DeliveryProviderRepository deliveryProviderRepository;

    public List<DeliveryProvider> getAllDeliveryProviders() {
        return deliveryProviderRepository.findAll();
    }

    public DeliveryProvider getDeliveryProviderById(Long deliveryProviderId) {
        return deliveryProviderRepository.findById(deliveryProviderId)
                .orElseThrow(() -> new RuntimeException("No such delivery provider"));
    }

    public DeliveryProvider createNewDeliveryProvider(DeliveryProvider deliveryProvider) {
        boolean isDeliveryProviderExists = deliveryProviderRepository
                .existsByName(deliveryProvider.getName());

        if(isDeliveryProviderExists) {
            throw new RuntimeException("Delivery provider with same name already exists");
        }

        return deliveryProviderRepository.save(deliveryProvider);
    }

    public void deleteDeliveryProviderById(Long deliveryProviderId) {
        deliveryProviderRepository.deleteById(deliveryProviderId);
    }
}

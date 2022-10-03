package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.repository.DeliveryProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliveryProviderService {
    private final DeliveryProviderRepository deliveryProviderRepository;
}

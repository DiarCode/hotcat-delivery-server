package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.entity.DeliveryProvider;
import kz.hotcat.hotcat.repository.DeliveryProviderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DeliveryProviderServiceTest {
    @Mock
    private DeliveryProviderRepository deliveryProviderRepository;

    @InjectMocks
    private DeliveryProviderService deliveryProviderService;

    private DeliveryProvider deliveryProvider;

    @BeforeEach
    public void setup(){
        deliveryProvider = DeliveryProvider.builder()
                .id(1L)
                .price(200.0)
                .description("delivery")
                .name("delivery")
                .build();
    }

    @DisplayName("Delete delivery provider by id")
    @Test
    void givenDeliveryProviderId_whenDeleteDeliveryProvider_thenNothing() {
        Long deliveryId = deliveryProvider.getId();

        willDoNothing().given(deliveryProviderRepository).deleteById(deliveryId);

        deliveryProviderService.deleteDeliveryProviderById(deliveryId);

        verify(deliveryProviderRepository, times(1)).deleteById(deliveryId);
    }

    @DisplayName("Create new delivery provider")
    @Test
    void givenDeliveryProviderObject_whenSaveDeliveryProvider_thenReturnDeliveryProviderObject() {
        given(deliveryProviderRepository.findById(deliveryProvider.getId()))
                .willReturn(Optional.empty());

        given(deliveryProviderRepository.save(deliveryProvider)).willReturn(deliveryProvider);

        DeliveryProvider savedDeliveryProvider = deliveryProviderService.createNewDeliveryProvider(deliveryProvider);

        assertThat(savedDeliveryProvider).isNotNull();
    }

    @DisplayName("Get delivery provider by id")
    @Test
    void givenDeliveryProviderId_whenGetDeliveryProviderById_thenReturnDeliveryProviderObject() {
        given(deliveryProviderRepository.findById(1L)).willReturn(Optional.of(deliveryProvider));

        DeliveryProvider savedDeliveryProvider = deliveryProviderService
                .getDeliveryProviderById(deliveryProvider.getId());

        assertThat(savedDeliveryProvider).isNotNull();
    }
}
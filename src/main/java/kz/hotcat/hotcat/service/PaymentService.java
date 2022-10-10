package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.dto.OrderDetailsDTO;
import kz.hotcat.hotcat.dto.PaymentDTO;
import kz.hotcat.hotcat.entity.*;
import kz.hotcat.hotcat.repository.AppUserRepository;
import kz.hotcat.hotcat.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final RestaurantService restaurantService;
    private final PaymentMethodService paymentMethodService;
    private final AppUserRepository appUserRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("No such payment"));
    }

    public void deletePaymentById(Long paymentId) {
        boolean isPaymentExists = paymentRepository.existsById(paymentId);

        if(!isPaymentExists) {
            throw new RuntimeException("No such payment");
        }

        paymentRepository.deleteById(paymentId);
    }

    @Transactional
    public Payment createNewPayment(PaymentDTO paymentDTO) {
        Restaurant restaurant = restaurantService.getRestaurantById(paymentDTO.getRestaurantId());
        AppUser user = appUserRepository.findById(paymentDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("No such user"));
        PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(paymentDTO.getPaymentMethodId());

        Payment payment = paymentRepository.save(new Payment());
        payment = Payment.builder()
                .totalPrice(payment.getTotalPrice())
                .timestamp(LocalDateTime.now())
                .paymentMethod(paymentMethod)
                .restaurant(restaurant)
                .user(user)
                .build();

        return payment;
    }

    @Transactional
    public Payment createNewPaymentWithOrder(PaymentDTO paymentDTO, Order order) {
        Restaurant restaurant = restaurantService.getRestaurantById(paymentDTO.getRestaurantId());
        AppUser user = appUserRepository.findById(paymentDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("No such user"));
        PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(paymentDTO.getPaymentMethodId());

        Payment payment = paymentRepository.save(new Payment());
        payment = Payment.builder()
                .paymentMethod(paymentMethod)
                .totalPrice(order.getTotalPrice())
                .timestamp(LocalDateTime.now())
                .restaurant(restaurant)
                .user(user)
                .build();

        paymentMethod.setPayment(payment);

        return payment;
    }
}

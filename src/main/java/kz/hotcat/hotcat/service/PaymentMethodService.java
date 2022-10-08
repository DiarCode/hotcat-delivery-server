package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.entity.PaymentMethod;
import kz.hotcat.hotcat.repository.PaymentMethodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod getPaymentMethodById(Long paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new RuntimeException("No such payment method"));
    }

    public void deletePaymentMethodById(Long paymentMethodId) {
        boolean isPaymentMethodExists = paymentMethodRepository.existsById(paymentMethodId);

        if(!isPaymentMethodExists) {
            throw new RuntimeException("No such payment method");
        }

        paymentMethodRepository.deleteById(paymentMethodId);
    }

    public PaymentMethod createNewPaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }
}

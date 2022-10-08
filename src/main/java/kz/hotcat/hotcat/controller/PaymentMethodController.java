package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.dto.PaymentDTO;
import kz.hotcat.hotcat.entity.Payment;
import kz.hotcat.hotcat.entity.PaymentMethod;
import kz.hotcat.hotcat.service.PaymentMethodService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment-methods")
@AllArgsConstructor
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    @GetMapping
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodService.getAllPaymentMethods();
    }

    @GetMapping("/{id}")
    public PaymentMethod getPaymentMethodById(@PathVariable(name = "id") Long paymentMethodId) {
        return paymentMethodService.getPaymentMethodById(paymentMethodId);
    }

    @PostMapping
    public PaymentMethod createNewPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.createNewPaymentMethod(paymentMethod);
    }

    @DeleteMapping("/{id}")
    public void deletePaymentMethodById(@PathVariable(name = "id") Long paymentMethodId) {
        paymentMethodService.deletePaymentMethodById(paymentMethodId);
    }
}

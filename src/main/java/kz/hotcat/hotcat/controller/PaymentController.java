package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.dto.PaymentDTO;
import kz.hotcat.hotcat.entity.DeliveryProvider;
import kz.hotcat.hotcat.entity.Payment;
import kz.hotcat.hotcat.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@AllArgsConstructor
public class PaymentController {
      private final PaymentService paymentService;

      @GetMapping
      public List<Payment> getAllPayments() {
            return paymentService.getAllPayments();
      }

      @GetMapping("/{id}")
      public Payment getPaymentById(@PathVariable(name = "id") Long paymentId) {
            return paymentService.getPaymentById(paymentId);
      }

      @PostMapping
      public Payment createNewPayment(@RequestBody PaymentDTO paymentDTO) {
            return paymentService.createNewPayment(paymentDTO);
      }

      @DeleteMapping("/{id}")
      public void deletePaymentById(@PathVariable(name = "id") Long paymentId) {
            paymentService.deletePaymentById(paymentId);
      }
}

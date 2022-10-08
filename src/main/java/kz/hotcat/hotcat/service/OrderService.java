package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.dto.OrderDTO;
import kz.hotcat.hotcat.dto.OrderDetailsDTO;
import kz.hotcat.hotcat.dto.OrderItemDTO;
import kz.hotcat.hotcat.entity.*;
import kz.hotcat.hotcat.repository.AppUserRepository;
import kz.hotcat.hotcat.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantService restaurantService;
    private final DeliveryProviderService deliveryProviderService;
    private final AppUserRepository appUserRepository;
    private final OrderItemService orderItemService;
    private final PaymentService paymentService;
    private final DeliveryDetailsService deliveryDetailsService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("No such delivery order"));
    }
   @Transactional
    public Order createNewOrder(OrderDTO orderDTO) {
        Restaurant restaurant = restaurantService
                .getRestaurantById(orderDTO.getRestaurantId());
        DeliveryProvider deliveryProvider = deliveryProviderService
                .getDeliveryProviderById(orderDTO.getDeliveryProviderId());
        AppUser user = appUserRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("No such user"));

        Order order = orderRepository.save(new Order());
        order.setRestaurant(restaurant);
        order.setDeliveryProvider(deliveryProvider);
        order.setAppUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setIsCooked(false);
        order.setIsDelivered(false);

        double totalPrice = 0;
        for (OrderItemDTO orderItemDTO : orderDTO.getOrderItemsList()){
            OrderItem orderItem = orderItemService.createNewOrderItem(orderItemDTO);
            orderItem.assignOrderItemToOrder(order);

            totalPrice += orderItem.getTotalPrice();
        }

        order.setTotalPrice(totalPrice + deliveryProvider.getPrice());

        return order;
    }

    public void deleteOrderById(Long orderId) {
        boolean isDeliveryProviderExists = orderRepository.existsById(orderId);

        if(!isDeliveryProviderExists) {
            throw new RuntimeException("No such delivery order");
        }

        orderRepository.deleteById(orderId);
    }

    public List<Order> getAllRecentOrders() {
        return orderRepository.findAllRecentOrders();
    }

    public List<Order> getAllRecentOrdersByUserId(Long userId) {
        return orderRepository.findAllRecentOrdersByUserId(userId);
    }

    @Transactional
    public Order fillPaymentAndDeliveryDetails(Long orderId, OrderDetailsDTO orderDetailsDTO) {
        Order order = getOrderById(orderId);

        Payment payment = paymentService.createNewPaymentWithOrder(orderDetailsDTO.getPayment(), order);
        DeliveryDetails deliveryDetails = deliveryDetailsService
                .createNewDeliveryDetails(orderDetailsDTO.getDeliveryDetails());

        order.setPayment(payment);
        order.setDeliveryDetails(deliveryDetails);

        return order;
    }

    @Transactional
    public Order changeCookingStatusOfOrderById(Long orderId) {
        Order order = getOrderById(orderId);
        boolean isOrderCooked = order.getIsCooked();

        if(isOrderCooked) {
            throw new RuntimeException("Order is already cooked");
        }

        order.setIsCooked(true);
        return order;
    }

    @Transactional
    public Order changeDeliveryStatusOfOrderById(Long orderId) {
        Order order = getOrderById(orderId);
        boolean isOrderDelivered = order.getIsDelivered();

        if(isOrderDelivered) {
            throw new RuntimeException("Order is already delivered");
        }

        order.setIsDelivered(true);
        return order;
    }
}

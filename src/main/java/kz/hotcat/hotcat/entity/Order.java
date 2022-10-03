package kz.hotcat.hotcat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;
    @OneToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy="order")
    private List<OrderItem> orderItemList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_provider_id")
    private DeliveryProvider deliveryProvider;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private AppUser user;
    private double totalPrice;
}

package kz.hotcat.hotcat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Restaurant restaurant;

    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

    @ManyToOne(cascade = CascadeType.ALL)
    private DeliveryProvider deliveryProvider;

    @ManyToOne(cascade = CascadeType.ALL)
    private AppUser appUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private double totalPrice;

    private LocalDateTime orderDate;

    private Boolean isCooked;
    private Boolean isDelivered;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_detail_id")
    private DeliveryDetails deliveryDetails;
}

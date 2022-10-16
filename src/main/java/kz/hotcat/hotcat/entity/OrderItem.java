package kz.hotcat.hotcat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="order_items")
public class OrderItem {
    @Id
    @SequenceGenerator(
            name = "order_items_sequence",
            sequenceName = "order_items_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_items_sequence"
    )
    @Column(name = "order_item_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id")
    private Food food;

    private int count;
    private double totalPrice;

    @ManyToMany(mappedBy = "orderItems",fetch = FetchType.EAGER)
    private List<Topping> toppings = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    public void assignOrderItemToOrder(Order order) {
        this.order = order;
    }
    public void adToppingToOrder(Topping topping) {
        this.toppings.add(topping);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderItem orderItem = (OrderItem) o;
        return id != null && Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

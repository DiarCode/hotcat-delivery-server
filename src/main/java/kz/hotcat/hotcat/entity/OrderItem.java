package kz.hotcat.hotcat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

    @OneToMany(mappedBy="orderItem", cascade = CascadeType.ALL)
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

}

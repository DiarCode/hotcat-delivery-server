package kz.hotcat.hotcat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="toppings")
public class Topping {
    @Id
    @SequenceGenerator(
            name = "topping_sequence",
            sequenceName = "topping_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "topping_sequence"
    )
    @Column(name = "topping_id")
    private Long id;

    private String name;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();

    public Topping(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

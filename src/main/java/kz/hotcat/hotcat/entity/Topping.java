package kz.hotcat.hotcat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    OrderItem orderItem;

    public Topping(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

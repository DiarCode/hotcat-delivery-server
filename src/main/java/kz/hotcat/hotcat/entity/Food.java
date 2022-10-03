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
@Table(name="foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "food_id")
    private Long id;
    private String image;
    private String name;
    private String description;
    private double number;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
}

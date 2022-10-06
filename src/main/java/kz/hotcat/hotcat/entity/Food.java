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
    @SequenceGenerator(
            name = "food_sequence",
            sequenceName = "food_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "food_sequence"
    )
    @Column(name = "food_id")
    private Long id;
    private String image;
    private String name;
    private String description;
    private double price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;

    public Food(String image, String name, String description, double price) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void assignFoodToMenu(Menu menu) {
        this.menu = menu;
    }
}

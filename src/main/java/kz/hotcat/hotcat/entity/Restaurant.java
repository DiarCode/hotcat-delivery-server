package kz.hotcat.hotcat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="restaurants")
public class Restaurant {
    @Id
    @SequenceGenerator(
            name = "restaurant_sequence",
            sequenceName = "restaurant_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "restaurant_sequence"
    )
    @Column(name = "restaurant_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean hasDelivery;
    @Column(nullable = false)
    private String deliveryTime;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String openHours;
    @Column(nullable = false)
    private String shortFoodDescription;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String rating;

    public Restaurant(String name, boolean hasDelivery, String deliveryTime, String address, String openHours, String shortFoodDescription, String description, String image, String rating) {
        this.name = name;
        this.hasDelivery = hasDelivery;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.openHours = openHours;
        this.shortFoodDescription = shortFoodDescription;
        this.description = description;
        this.image = image;
        this.rating = rating;
    }
}

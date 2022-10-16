package kz.hotcat.hotcat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Payment> paymentList;

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

    public void assignMenuToRestaurant(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Restaurant that = (Restaurant) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

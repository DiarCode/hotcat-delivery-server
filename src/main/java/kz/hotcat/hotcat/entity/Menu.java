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
@Table(name = "menus")
public class Menu {
    @Id
    @SequenceGenerator(
            name = "menu_sequence",
            sequenceName = "menu_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "menu_sequence"
    )
    @Column(name = "menu_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy="menu", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Food> foodList;

    @OneToOne(mappedBy="menu", cascade = CascadeType.ALL)
    @JsonIgnore
    private Restaurant restaurant;

    public Menu(String name) {
        this.name = name;
        this.foodList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Menu menu = (Menu) o;
        return id != null && Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

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
    private List<Food> foodList;

    @OneToOne(mappedBy="menu", cascade = CascadeType.ALL)
    @JsonIgnore
    private Restaurant restaurant;

    public Menu(String name) {
        this.name = name;
        this.foodList = new ArrayList<>();
    }
}

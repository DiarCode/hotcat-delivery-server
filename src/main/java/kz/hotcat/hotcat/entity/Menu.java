package kz.hotcat.hotcat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "menu_id")
    private Long id;

    @OneToMany(mappedBy="menu")
    private List<Food> foodList;
}

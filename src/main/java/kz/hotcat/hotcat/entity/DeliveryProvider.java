package kz.hotcat.hotcat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_providers")
public class DeliveryProvider {
    @Id
    @SequenceGenerator(
            name = "delivery_providers_sequence",
            sequenceName = "delivery_providers_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "delivery_providers_sequence"
    )
    @Column(name = "delivery_provider_id")
    private Long id;
    private String name;
    private String description;
    private double price;

    public DeliveryProvider(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}

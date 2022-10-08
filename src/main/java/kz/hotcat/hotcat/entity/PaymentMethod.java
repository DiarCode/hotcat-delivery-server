package kz.hotcat.hotcat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "payment_methods")
public class PaymentMethod {
    @Id
    @SequenceGenerator(
            name = "payment_method_sequence",
            sequenceName = "payment_method_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_method_sequence"
    )
    @Column(name = "payment_method_id")
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Payment payment;

    public PaymentMethod(String name) {
        this.name = name;
    }
}

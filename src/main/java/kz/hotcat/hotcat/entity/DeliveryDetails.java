package kz.hotcat.hotcat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_details")
public class DeliveryDetails {
    @Id
    @SequenceGenerator(
            name = "delivery_detail_sequence",
            sequenceName = "delivery_detail_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "delivery_detail_sequence"
    )
    @Column(name = "delivery_detail_id")
    private Long id;

    private String address;
    private String city;
    private String contactNumber;
    private String additionInfo;

    public DeliveryDetails(String address, String city, String contactNumber, String additionInfo) {
        this.address = address;
        this.city = city;
        this.contactNumber = contactNumber;
        this.additionInfo = additionInfo;
    }
}

package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Furniture")
@Getter
@Setter
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FurnitureId")
    private int furnitureId;

    @Column(name = "FacilityName")
    private String facilityName;

    @Column(name = "Quality")
    private String quality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private FurnitureType furnitureType;

    @Column(name = "Price")
    private java.math.BigDecimal price;
}
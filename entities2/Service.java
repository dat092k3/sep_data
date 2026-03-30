package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Service")
@Getter
@Setter
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServiceId")
    private int serviceId;

    @Column(name = "ServiceName")
    private String serviceName;

    @Column(name = "BasePrice")
    private BigDecimal basePrice;

    @Column(name = "Category")
    private String category;

}
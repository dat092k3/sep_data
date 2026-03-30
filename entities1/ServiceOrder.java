package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ServiceOrder")
@Getter
@Setter
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private int orderId;

    @Column(name = "OrderTime")
    private LocalDateTime orderTime;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "OrderPrice")
    private BigDecimal orderPrice;

    @Column(name = "Description", columnDefinition = "varchar(255)")
    private String description;

    @Column(name = "PaymentStatus", columnDefinition = "varchar(50)")
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "StayId")
    private Stay stay;

    @ManyToOne
    @JoinColumn(name = "ServiceId")
    private Service service;
}
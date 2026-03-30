package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "roomtypeinventory")
@Getter
@Setter
public class RoomTypeInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InventoryId")
    private int inventoryId;

    @Column(name = "WorkDate")
    private Date workDate;

    @Column(name = "Availability")
    private int availability;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "IsClosed")
    private boolean isClosed;

    @Column(name = "MinStay")
    private int minStay;

    @ManyToOne
    @JoinColumn(name = "RoomTypeId")
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "RatePlanId")
    private RatePlan ratePlan;
}
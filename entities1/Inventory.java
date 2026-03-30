package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Inventory")
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InventoryId")
    private int inventoryId;

    @Column(name = "InventoryName")
    private String inventoryName;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Stock")
    private int stock;

    // Thêm Unit theo ERD
    @Column(name = "Unit")
    private String unit;

    @ManyToOne
    @JoinColumn(name = "BranchId")
    private Branch branch;
}
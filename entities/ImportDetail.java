package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "ImportDetail")
@Getter
@Setter
public class ImportDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImportDetailId")
    private int importDetailId;

    @Column(name = "ImportType")
    private String importType;

    @Column(name = "ImportQuantity")
    private int importQuantity;

    @Column(name = "UnitPrice")
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "ImportReceiptId")
    private ImportReceipt importReceipt;

    @ManyToOne
    @JoinColumn(name = "FurnitureId")
    private Furniture furniture;

    @ManyToOne
    @JoinColumn(name = "InventoryId")
    private Inventory inventory;
}
package sep490.entities;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "ImportReceiptDetail")
@Data
public class ImportReceiptDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImportReceiptDetailId")
    private int importReceiptDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ImportReceiptId")
    private ImportReceipt importReceipt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InventoryId")
    private Inventory inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FurnitureId")
    private Furniture furniture;

    // SỬA Ở ĐÂY: Đổi ImportQuantity thành quantity (giống hệt tên cột trong DB của bạn)
    @Column(name = "quantity")
    private int importQuantity;

    @Column(name = "UnitPrice")
    private BigDecimal unitPrice;
}
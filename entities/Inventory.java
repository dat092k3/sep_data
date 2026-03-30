package sep490.entities;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Inventory")
@Data // Tự sinh Get/Set với Lombok
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InventoryId")
    private int inventoryId;

    @Column(name = "InventoryName")
    private String inventoryName;

    @Column(name = "Unit") // Thêm trường này theo UI (ĐVT: Cái, Gói)
    private String unit;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Stock")
    private int stock;

    @Column(name = "Date")
    private LocalDateTime date; // Ngày tạo

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BranchId")
    private Branch branch; // Ánh xạ tới chi nhánh
}
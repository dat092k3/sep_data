package sep490.entities;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "MonthlyInventoryReport")
@Data
public class MonthlyInventoryReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InventoryMonthlyReportId")
    private int reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InventoryId")
    private Inventory inventory;

    @Column(name = "ReportMonth")
    private int month;

    @Column(name = "ReportYear") // Phải có Năm để không nhầm tháng 3 năm nay với năm sau
    private int year;

    @Column(name = "OpeningStock")
    private int openingStock; // Tồn đầu

    @Column(name = "ImportQuantity")
    private int importQuantity; // Hàng mua trong tháng

    @Column(name = "ClosingStock")
    private int closingStock; // Tồn cuối (User nhập)

    @Column(name = "UsedQuantity")
    private int usedQuantity; // Sử dụng
}
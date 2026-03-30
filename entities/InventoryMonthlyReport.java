package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "InventoryMonthlyReport")
@Getter
@Setter
public class InventoryMonthlyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InventoryMonthlyReportId")
    private int inventoryMonthlyReportId;

    @Column(name = "ReportMonth")
    private String reportMonth; // Format: "YYYY-MM"

    @Column(name = "OpeningStock")
    private int openingStock;

    @Column(name = "ImportQuantity")
    private int importQuantity;

    @Column(name = "ClosingStock")
    private int closingStock;

    @Column(name = "UsedQuantity")
    private int usedQuantity;

    @Column(name = "Note")
    private String note;

    @Column(name = "IsSaved")
    private boolean isSaved = false;

    @Column(name = "SavedAt")
    private LocalDateTime savedAt;

    @Column(name = "CreatedAt", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CreatedBy")
    private Users createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InventoryId")
    private Inventory inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BranchId")
    private Branch branch;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
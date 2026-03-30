package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "MonthlyExpense", uniqueConstraints = {
        // Ràng buộc chống duplicate: 1 cơ sở - 1 tháng - 1 năm - 1 danh mục chỉ có 1 record
        @UniqueConstraint(columnNames = {"BranchId", "ReportMonth", "ReportYear", "Category"})
})
@Getter
@Setter
public class MonthlyExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExpenseId")
    private int expenseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BranchId", nullable = false)
    private Branch branch;

    @Column(name = "ReportMonth", nullable = false)
    private int reportMonth;

    @Column(name = "ReportYear", nullable = false)
    private int reportYear;

    @Column(name = "Category", nullable = false)
    private String category; // "Tiền điện", "Tiền nước sinh hoạt", "Giặt là"...

    @Column(name = "Amount")
    private BigDecimal amount; // Tiền chi. Nếu "Chưa có" thì lưu null hoặc 0

    @Column(name = "Note", length = 500)
    private String note; // Ghi chú

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

    // Tự động cập nhật thời gian mỗi khi sửa báo cáo
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
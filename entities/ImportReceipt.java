package sep490.entities;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ImportReceipt")
@Data
public class ImportReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImportReceiptId")
    private int importReceiptId;

    @Column(name = "ImportDate")
    private LocalDateTime importDate;

    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "BranchId")
    private Branch branch;
}
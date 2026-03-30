package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ImportReceipt")
@Getter
@Setter
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
    @JoinColumn(name = "CreatedBy")
    private Users createdBy;

    @ManyToOne
    @JoinColumn(name = "BranchId")
    private Branch branch;
}
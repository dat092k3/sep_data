package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "InvoiceDetail")
@Getter
@Setter
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InvoiceDetailId")
    private Integer invoiceDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InvoiceId", nullable = false)
    private Invoice invoice;

    @Column(name = "Description", length = 255)
    private String description;

    // ROOM | SERVICE | SURCHARGE | TAX | DISCOUNT
    @Column(name = "ItemType", length = 50)
    private String itemType;

    @Column(name = "Amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "Quantity")
    private int quantity = 1;

    // Optional FK → ServiceOrder.OrderId for traceability
    @Column(name = "ReferenceId")
    private Integer referenceId;

    // Optional FK → Payment.PaymentId: Dòng nào đã được thanh toán bởi payment cụ thể nào
    // null = chưa map được payment (dữ liệu cũ) hoặc chưa thanh toán
    @Column(name = "PaymentId")
    private Integer paymentId;

    // false = chưa thanh toán (gộp vào checkout), true = đã trả ngay tại quầy
    @Column(name = "IsPaid", nullable = false)
    private boolean isPaid = false;

    @Column(name = "CreatedAt", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

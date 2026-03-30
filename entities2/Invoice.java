package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Invoice")
@Getter
@Setter
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InvoiceId")
    private int invoiceId;

    @OneToOne
    @JoinColumn(name = "BookingId", nullable = false)
    private Booking booking;

    @Column(name = "TotalAmount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "Status", nullable = false) // UNPAID, PAID, CANCELLED
    private String status;

    @Column(name = "CreatedAt", updatable = false)
    private LocalDateTime createdAt;

    // Thêm dòng này vào dưới trường status trong file Invoice.java
    @Column(name = "TransactionCode")
    private String transactionCode;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Payment> payments;



    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

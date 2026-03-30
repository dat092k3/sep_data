package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Payment") // Tên bảng viết hoa
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentId") // Viết hoa chữ P
    private Integer paymentId;  // Đổi Long thành Integer cho khớp với SQL INT

    @Column(name = "Amount")    // Viết hoa chữ A
    private BigDecimal amount;

    // Lưu chuỗi "STRIPE", "CASH"...
    @Column(name = "PaymentMethod") // Viết hoa P, M
    private String paymentMethod;

    @Column(name = "PaidAt")        // Viết hoa P, A
    private LocalDateTime paidAt;

    @ManyToOne
    @JoinColumn(name = "InvoiceId") // Viết hoa I, Id
    private Invoice invoice;

    @Column(name = "CreatedAt")     // Viết hoa C, A
    private LocalDateTime createdAt;

    // Lưu chuỗi "PENDING", "COMPLETED"...
    @Column(name = "PaymentStatus") // Viết hoa P, S
    private String paymentStatus;

    @Column(name = "ProviderTxnId") // Viết hoa P, T, I
    private String providerTxnId;

    @ManyToOne
    @JoinColumn(name = "StaffId")   // Viết hoa S, Id
    private Users staff;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.paymentStatus == null) {
            this.paymentStatus = "PENDING";
        }
    }
}
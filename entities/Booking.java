package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import java.time.LocalDateTime;

@Entity
@Table(name = "Booking")
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingId")
    private int bookingId;

    @Column(name = "BookingCode", unique = true, nullable = false)
    private String bookingCode; // Mã booking duy nhất để tra cứu, vd: BR-10023

    @Column(name = "Source")
    private String source;

    @Column(name = "ChannelBookingId")
    private String channelBookingId;

    @Column(name = "Status")
    private String status;
    
    @Column(name = "ArrivalDate")
    private LocalDateTime arrivalDate;

    @Column(name = "DepartureDate")
    private LocalDateTime departureDate;

    @Column(name = "ActualCheckIn")
    private LocalDateTime actualCheckIn;

    @Column(name = "ActualCheckOut")
    private LocalDateTime actualCheckOut;

    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    @Column(name = "SpecialRequests", length = 500)
    private String specialRequests;

    @Column(name = "CreatedAt", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BranchId")
    private Branch branch;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CustomerId")
    private Guest customer;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingDetail> bookingDetails;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        // Tự động tạo bookingCode nếu nó chưa được set
        if (this.bookingCode == null || this.bookingCode.isEmpty()) {
            // Tạo một mã ngẫu nhiên, dễ đọc và khó đoán
            this.bookingCode = "BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }
}
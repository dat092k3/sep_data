package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "branch")
@Getter
@Setter
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BranchId")
    private int branchId;

    @Column(name = "BranchName", nullable = false)
    private String branchName;

    @Column(name = "PropertyType") // hotel, apartment, villa...
    private String propertyType = "hotel";

    @Column(name = "Address")
    private String address;

    @Column(name = "City")
    private String city;

    @Column(name = "Country")
    private String country = "VN";

    @Column(name = "Timezone")
    private String timezone = "Asia/Ho_Chi_Minh";

    @Column(name = "Currency")
    private String currency = "VND";

    @Column(name = "ContactNumber")
    private String contactNumber;

    @Column(name = "ChannelPropertyId") // ID nhận về từ Channex
    private String channelPropertyId;

    @Column(name = "CreatedAt", updatable = false)
    private Timestamp createdAt;

    // Tự động gán thời gian tạo
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }
}
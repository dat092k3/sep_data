package sep490.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "GuestAccessToken")
@Data
public class GuestAccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Session token (UUID) – được tạo sau khi OTP verify thành công, dùng để lấy bookings */
    @Column(name = "Token", nullable = false, unique = true)
    private String token;

    /** OTP 6 chữ số – hết hạn sau 15 phút kể từ khi tạo */
    @Column(name = "OtpCode", length = 6)
    private String otpCode;

    /** Lưu email thay vì Guest entity, để hỗ trợ nhiều guest dùng cùng email */
    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "ExpiryDate", nullable = false)
    private Date expiryDate;

    /** OTP đã được verify thành công chưa  */
    @Column(name = "OtpVerified", nullable = false)
    private boolean otpVerified = false;

    /**
     * Set thời gian hết hạn tính từ thời điểm hiện tại.
     * @param minutes số phút trước khi hết hạn
     */
    public void setExpiryInMinutes(int minutes) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        this.expiryDate = now.getTime();
    }

    /** Giữ backward-compat với code cũ (dùng giờ) */
    public void setExpiryDate(int hours) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, hours);
        this.expiryDate = now.getTime();
    }

    public boolean isExpired() {
        return this.expiryDate.before(new Date());
    }
}

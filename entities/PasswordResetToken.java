package sep490.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Users user;

    private Date expiryDate;

    // Hàm tính ngày hết hạn (ví dụ 15 phút sau khi tạo)
    public void setExpiryDate(int minutes){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        this.expiryDate = now.getTime();
    }
}
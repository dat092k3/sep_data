package sep490.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "RatePlan")
@Getter
@Setter
public class RatePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RatePlanId")
    private int ratePlanId;

    @Column(name = "Name")
    private String name;

    @Column(name = "ChannelRatePlanId")
    private String channelRatePlanId;

    @Column(name = "Price")
    private BigDecimal price;

    @JsonIgnore  // Prevent circular reference back to RoomType
    @ManyToOne
    @JoinColumn(name = "RoomTypeId")
    private RoomType roomType;


    @Column(name = "CancellationType")
    private String cancellationType;

    @Column(name = "FreeCancelBeforeDays")
    private Integer freeCancelBeforeDays;

    // PREPAID / PAY_AT_HOTEL ...
    @Column(name = "PaymentType")
    private String paymentType;


}
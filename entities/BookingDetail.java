package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "BookingDetail") // Đổi tên bảng cho rõ ràng hơn
@Getter
@Setter
public class BookingDetail {

    @EmbeddedId
    private BookingDetailId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookingId")
    @JoinColumn(name = "BookingId")
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roomTypeId")
    @JoinColumn(name = "RoomTypeId")
    private RoomType roomType;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("priceModifierId")
    @JoinColumn(name = "PriceModifierId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PriceModifier priceModifier;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    @Column(name = "PriceAtBooking", nullable = false)
    private BigDecimal priceAtBooking;

    @Embeddable
    @Getter
    @Setter
    public static class BookingDetailId implements Serializable {
        private int bookingId;
        private int roomTypeId;
        private int priceModifierId;

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            BookingDetailId that = (BookingDetailId) o;
            return bookingId == that.bookingId &&
                    roomTypeId == that.roomTypeId &&
                    priceModifierId == that.priceModifierId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(bookingId, roomTypeId, priceModifierId);
        }
    }
}
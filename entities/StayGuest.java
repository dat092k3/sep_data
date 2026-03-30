package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Stay_Guest")
@Getter
@Setter
public class Stay_Guest {

    @EmbeddedId
    private StayGuestId id;

    @ManyToOne
    @MapsId("stayId")
    @JoinColumn(name = "StayId")
    private Stay stay;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "CustomerId")
    private Guest guest;

    @Column(name = "IsPrimary")
    private boolean isPrimary;

    @Embeddable
    @Getter
    @Setter
    public static class StayGuestId implements Serializable {
        private int stayId;
        private int customerId;
    }
}
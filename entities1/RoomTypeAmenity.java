package sep490.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "roomtype_amenity")
public class RoomTypeAmenity {

    @EmbeddedId
    private RoomTypeAmenityId id;

    @JsonIgnore  // Prevent circular reference back to RoomType
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roomTypeId")
    @JoinColumn(name = "roomTypeId")
    private RoomType roomType;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("amenityId")
    @JoinColumn(name = "AmenityId")
    private Amenity amenity;
}

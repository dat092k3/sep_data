package sep490.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class RoomTypeAmenityId implements Serializable {

    private Integer roomTypeId;
    private Integer amenityId;
}

package sep490.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "amenity")
@Getter
@Setter
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AmenityId")
    private int amenityId;

    @Column(name = "AmenityName")
    private String amenityName;

    @Column(name = "Category")
    private String category;

    /* ================= RELATION ================= */

    // Amenity <-> RoomType (N-N qua bảng trung gian)
    @JsonIgnore  // Prevent circular reference
    @OneToMany(mappedBy = "amenity", fetch = FetchType.LAZY)
    private List<RoomTypeAmenity> roomTypeAmenities;
}

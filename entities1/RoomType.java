package sep490.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "roomtype")
@Getter
@Setter
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoomTypeId")
    private int roomTypeId;

    @Column(name = "ChannelRoomTypeId")
    private String channelRoomTypeId;

    @Column(name = "Name")
    private String name;

    @Column(name = "MaxAdult")
    private int maxAdult;

    @Column(name = "MaxChildren")
    private int maxChildren;

    @Column(name = "BasePrice")
    private BigDecimal basePrice;

    @Column(name = "Image")
    private String image;

    @Column(name = "Description")
    private String description;

    @Column(name = "Area")
    private Integer area;   // m²

    @Column(name = "BedType")
    private String bedType; // Single / Double / Queen / King

    @Column(name = "BedCount")
    private Integer bedCount;

    @JsonIgnore  // Prevent circular reference
    @OneToMany(mappedBy = "roomType", fetch = FetchType.LAZY)
    private List<RoomTypeAmenity> roomTypeAmenities;

    /* ================= RELATION ================= */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BranchId")
    private Branch branch;
}

package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Room_Furniture")
@Getter
@Setter
public class Room_Furniture {

    @EmbeddedId
    private RoomFurnitureId id;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "RoomId")
    private Room room;

    @ManyToOne
    @MapsId("furnitureId")
    @JoinColumn(name = "FurnitureId")
    private Furniture furniture;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Status")
    private String status;

    @Embeddable
    @Getter
    @Setter
    public static class RoomFurnitureId implements Serializable {
        private int roomId;
        private int furnitureId;
    }
}
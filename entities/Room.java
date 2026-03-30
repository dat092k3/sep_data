package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Room")
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoomId")
    private Integer roomId;

    @Column(name = "RoomName")
    private String roomName;

    @Column(name = "PhysicalStatus")
    private String physicalStatus;

    @Column(name = "Floor")
    private Integer floor;

    @ManyToOne
    @JoinColumn(name = "RoomTypeId")
    private RoomType roomType;

    @Column(name = "maintenance_reason")
    private String maintenanceReason;

    @Column(name = "maintenance_reported_at")
    private LocalDateTime maintenanceReportedAt;
}
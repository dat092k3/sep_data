package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Incident")
@Getter
@Setter
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IncidentId")
    private int incidentId;

    @ManyToOne
    @JoinColumn(name = "RoomId", nullable = false)
    private Room room;

    @Column(name = "Description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "Status", nullable = false)
    private String status; // OPEN, CLOSED, etc.

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "CreatedBy")
    private Users createdBy;

    @Column(name = "ResolvedAt")
    private LocalDateTime resolvedAt;

    @ManyToOne
    @JoinColumn(name = "ResolvedBy")
    private Users resolvedBy;

    @Column(name = "Resolution", columnDefinition = "TEXT")
    private String resolution; // How the issue was resolved
    
}


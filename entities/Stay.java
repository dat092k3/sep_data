package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Stay")
@Getter
@Setter
public class Stay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StayId")
    private int stayId;

    @Column(name = "ActualCheckIn")
    private LocalDateTime actualCheckIn;

    @Column(name = "ActualCheckOut")
    private LocalDateTime actualCheckOut;

    @ManyToOne
    @JoinColumn(name = "BookingId")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "RoomId")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "StaffId")
    private Users staff;
}
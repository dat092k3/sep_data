package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "SyncLog")
@Getter
@Setter
public class SyncLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogId")
    private int logId;

    @Column(name = "ChannelName")
    private String channelName;

    @Column(name = "Type")
    private String type;

    @Column(name = "Status")
    private String status;

    @Column(name = "Payload", columnDefinition = "TEXT")
    private String payload;

    @Column(name = "ErrorMessage", columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "CreatedAt")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "StaffId")
    private Users staff;
}
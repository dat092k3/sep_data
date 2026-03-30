package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ShiftLog")
@Getter
@Setter
public class ShiftLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShiftLogId")
    private int shiftLogId;

    @Column(name = "StartTime")
    private LocalDateTime startTime;

    @Column(name = "EndTime")
    private LocalDateTime endTime;

    @Column(name = "CashBegin")
    private BigDecimal cashBegin;

    @Column(name = "CashEnd")
    private BigDecimal cashEnd;

    @ManyToOne
    @JoinColumn(name = "StaffId")
    private Users staff;

    @ManyToOne
    @JoinColumn(name = "BranchId")
    private Branch branch;
}
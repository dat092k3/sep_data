package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sep490.util.MonthDayRange;
import sep490.util.MonthDayRangeStringAttributeConverter;

@Entity
@Table(name = "CancellationPolicy")
@Getter
@Setter
public class CancellationPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PolicyId")
    private int id;

    @Column(name = "Type")
    private String type;

    @Column(name = "Name")
    private String name;

    @Column(name = "IsActive")
    private Boolean active = Boolean.TRUE;

    @Column(name = "ActiveTime")
    @Convert(converter = MonthDayRangeStringAttributeConverter.class)
    private MonthDayRange activeTime;

    @Column(name = "BranchId")
    private Integer branchId;

    @Column(name = "DateRange")
    private String dateRange;

    @Column(name = "PrepaidRate", nullable = false)
    private Integer prepaidRate = 0;

    @Column(name = "RefunRate", nullable = false)
    private Integer refunRate = 0;

    @PrePersist
    @PreUpdate
    protected void normalizeDefaults() {
        if (active == null) {
            active = Boolean.TRUE;
        }
        if (prepaidRate == null) {
            prepaidRate = 0;
        }
        if (refunRate == null) {
            refunRate = 0;
        }

        validatePercent(prepaidRate, "prepaidRate");
        validatePercent(refunRate, "refunRate");
        validateActiveTimeRange();
    }

    private void validatePercent(Integer value, String fieldName) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException(fieldName + " must be between 0 and 100");
        }
    }

    private void validateActiveTimeRange() {
        if (activeTime == null) {
            return;
        }

        if (activeTime.getStart() == null || activeTime.getEnd() == null) {
            throw new IllegalArgumentException("activeTime must include both start and end");
        }
    }
}

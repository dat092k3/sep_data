package sep490.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sep490.enums.AdjustmentType;
import sep490.enums.RatePlanModifier;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Entity
@Table(name = "PriceModifier")
@Getter
@Setter
public class PriceModifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PriceModifierId")
    private int priceModifierId;

    @JsonIgnore  // Prevent circular reference back to RoomType
    @ManyToOne
    @JoinColumn(name = "RoomTypeId")
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type")
    private RatePlanModifier type;

    @Column(name = "Name")
    private String name;

    // Store flexible condition params, e.g. {"start":"2026-06-01","end":"2026-06-05"}
    @Column(name = "Metadata", columnDefinition = "TEXT")
    private String metadata;

    @Enumerated(EnumType.STRING)
    @Column(name = "AdjustmentType")
    private AdjustmentType adjustmentType;

    @Column(name = "AdjustmentValue", precision = 10, scale = 2)
    private BigDecimal adjustmentValue;

    @Column(name = "IsActive")
    private Boolean active = true;

    public boolean isActive() {
        return Boolean.TRUE.equals(active);
    }

    public void setActive(Boolean active) {
        this.active = active == null ? Boolean.TRUE : active;
    }

    @PrePersist
    @PreUpdate
    protected void onCreate() {
        if (this.active == null) {
            this.active = Boolean.TRUE;
        }
        validateAdjustmentValue();
    }

    private void validateAdjustmentValue() {
        if (adjustmentType == null || adjustmentValue == null) {
            return;
        }

        if (adjustmentType == AdjustmentType.PERCENT
                && (adjustmentValue.compareTo(BigDecimal.valueOf(-100)) < 0
                || adjustmentValue.compareTo(BigDecimal.valueOf(100)) > 0)) {
            throw new IllegalArgumentException("adjustmentValue must be between -100 and 100 when adjustmentType is PERCENT");
        }
    }

    public void buildMetadata(Map<String, Object> input) {
        if (type == null) {
            throw new IllegalArgumentException("type is required before building metadata");
        }

        Map<String, Object> normalized = switch (type) {
            case DATE_RANGE -> buildDateRangeMetadata(input);
            case DAY_OF_WEEK -> buildDayOfWeekMetadata(input);
            case ADVANCE_BOOKING -> buildAdvanceBookingMetadata(input);
            case LENGTH_OF_STAY -> buildLengthOfStayMetadata(input);
            case OCCUPANCY -> buildOccupancyMetadata(input);
            case AVAILABILITY -> buildAvailabilityMetadata(input);
            case POLICY -> buildPolicyMetadata(input);
        };

        this.metadata = toJson(normalized);
    }

    private Map<String, Object> buildAdvanceBookingMetadata(Map<String, Object> input) {
        return buildNonNegativeRange(input, "minDaysBefore", "maxDaysBefore");
    }

    private Map<String, Object> buildLengthOfStayMetadata(Map<String, Object> input) {
        return buildNonNegativeRange(input, "minNights", "maxNights");
    }

    private Map<String, Object> buildOccupancyMetadata(Map<String, Object> input) {
        return buildNonNegativeRange(input, "minRooms", "maxRooms");
    }

    private Map<String, Object> buildAvailabilityMetadata(Map<String, Object> input) {
        return buildNonNegativeRange(input, "minAvailableRooms", "maxAvailableRooms");
    }

    private Map<String, Object> buildDateRangeMetadata(Map<String, Object> input) {
        String start = getRequiredString(input, "start");
        String end = getRequiredString(input, "end");

        LocalDate startDate = parseDate(start, "start");
        LocalDate endDate = parseDate(end, "end");
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("end must be on or after start for type " + type);
        }

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("start", startDate.toString());
        data.put("end", endDate.toString());
        data.put("inclusive", true);
        return data;
    }

    private Map<String, Object> buildDayOfWeekMetadata(Map<String, Object> input) {
        Object days = input.get("days");
        if (!(days instanceof List<?> dayList) || dayList.isEmpty()) {
            throw new IllegalArgumentException("days must be a non-empty array when type is DAY_OF_WEEK");
        }

        List<String> normalizedDays = new ArrayList<>();
        for (Object day : dayList) {
            String dayName = String.valueOf(day).trim().toUpperCase(Locale.ROOT);
            try {
                DayOfWeek.valueOf(dayName);
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Invalid day value: " + day + " for type " + type);
            }
            if (!normalizedDays.contains(dayName)) {
                normalizedDays.add(dayName);
            }
        }

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("days", normalizedDays);
        return data;
    }

    private Map<String, Object> buildNonNegativeRange(Map<String, Object> input, String minField, String maxField) {
        Integer min = getOptionalInteger(input, minField);
        Integer max = getOptionalInteger(input, maxField);

        Map<String, Object> data = new LinkedHashMap<>();
        if (min != null) {
            if (min < 0) {
                throw new IllegalArgumentException(minField + " must be >= 0 for type " + type);
            }
            data.put(minField, min);
        }
        if (max != null) {
            if (max < 0) {
                throw new IllegalArgumentException(maxField + " must be >= 0 for type " + type);
            }
            data.put(maxField, max);
        }

        if (data.isEmpty()) {
            throw new IllegalArgumentException("metadata must include " + minField + " or " + maxField + " for type " + type);
        }
        if (min != null && max != null && min > max) {
            throw new IllegalArgumentException(minField + " cannot be greater than " + maxField + " for type " + type);
        }
        return data;
    }

    private Map<String, Object> buildPolicyMetadata(Map<String, Object> input) {
        Integer policyId = getOptionalInteger(input, "policyId");
        if (policyId == null || policyId <= 0) {
            throw new IllegalArgumentException("policyId is required for type " + type);
        }

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("policyId", policyId);
        return data;
    }

    private LocalDate parseDate(String rawDate, String fieldName) {
        try {
            return LocalDate.parse(rawDate);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException(fieldName + " must use yyyy-MM-dd format for type " + type);
        }
    }

    private String getRequiredString(Map<String, Object> input, String field) {
        Object value = input.get(field);
        if (value == null || String.valueOf(value).isBlank()) {
            throw new IllegalArgumentException(field + " is required for type " + type);
        }
        return String.valueOf(value).trim();
    }

    private Integer getOptionalInteger(Map<String, Object> input, String field) {
        Object value = input.get(field);
        if (value == null) {
            return null;
        }
        if (value instanceof Number number) {
            return number.intValue();
        }
        try {
            return Integer.parseInt(String.valueOf(value));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(field + " must be an integer for type " + type);
        }
    }

    private String toJson(Map<String, Object> data) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        boolean first = true;
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (!first) {
                builder.append(",");
            }
            first = false;
            builder.append("\"")
                    .append(escapeJson(entry.getKey()))
                    .append("\":")
                    .append(toJsonValue(entry.getValue()));
        }
        builder.append("}");
        return builder.toString();
    }

    private String toJsonValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Number || value instanceof Boolean) {
            return value.toString();
        }
        if (value instanceof List<?> list) {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) {
                    builder.append(",");
                }
                builder.append(toJsonValue(list.get(i)));
            }
            builder.append("]");
            return builder.toString();
        }
        return "\"" + escapeJson(String.valueOf(value)) + "\"";
    }

    private String escapeJson(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}
package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ChannelMapping")
@Getter
@Setter
public class ChannelMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MappingId")
    private int mappingId;

    @Column(name = "OtaRoomTypeId")
    private String otaRoomTypeId;

    @Column(name = "OtaRateId")
    private String otaRateId;

    @Column(name = "PriceModifierId")
    private int priceModifierId;

    @ManyToOne
    @JoinColumn(name = "OtaAccountId")
    private OtaAccount otaAccount;
}
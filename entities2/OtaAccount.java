package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "OtaAccount")
@Getter
@Setter
public class OtaAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OtaAccountId")
    private int otaAccountId;

    @Column(name = "ChannelName")
    private String channelName;

    @Column(name = "OtaPropertyId")
    private String otaPropertyId;

    @Column(name = "Status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "BranchId")
    private Branch branch;
}
package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "User_Branch")
@Getter
@Setter
public class User_Branch {

    @EmbeddedId
    private UserBranchId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "UserId")
    private Users user;

    @ManyToOne
    @MapsId("branchId")
    @JoinColumn(name = "BranchId")
    private Branch branch;

    @Column(name = "IsDefault")
    private boolean isDefault;

    @Embeddable
    @Getter
    @Setter
    public static class UserBranchId implements Serializable {
        private int userId;
        private int branchId;
    }
}
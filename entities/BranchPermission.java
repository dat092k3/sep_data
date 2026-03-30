package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "BranchPermission")
@Getter
@Setter
public class BranchPermission {
    
    @EmbeddedId
    private BranchPermissionId id;
    
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "UserId")
    private Users user;
    
    @ManyToOne
    @MapsId("branchId")
    @JoinColumn(name = "BranchId")
    private Branch branch;
    
    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "PermissionId")
    private Permission permission;
    
    @Embeddable
    @Getter
    @Setter
    public static class BranchPermissionId implements Serializable {
        @Column(name = "UserId")
        private int userId;
        
        @Column(name = "BranchId")
        private int branchId;
        
        @Column(name = "PermissionId")
        private int permissionId;
    }
}

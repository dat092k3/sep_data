package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Permissions")
@Getter
@Setter
public class Permission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PermissionId")
    private int permissionId;
    
    @Column(name = "PermissionName", unique = true, nullable = false)
    private String permissionName; // MANAGE_BRANCH, VIEW_REPORTS, etc.
    
    @Column(name = "Description")
    private String description;
}

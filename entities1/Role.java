package sep490.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(name = "RoleName", unique = true, nullable = false)
    private String roleName; // ROLE_ADMIN, ROLE_MANAGER, ROLE_STAFF

    @Column(name = "Description")
    private String description;
}

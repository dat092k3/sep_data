package sep490.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private int userId;

    @Column(name = "Username", unique = true)
    private String username;

    @Column(name = "PasswordHash")
    private String passwordHash;

    @Column(name = "Email")
    private String email;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "Image")
    private String image;

    @Column(name = "Status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DefaultBranchId")
    private Branch defaultBranch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RoleId")
    private Role role;

    /** RoleId read-only để fallback khi relation role null (vẫn hiển thị đúng trên web). */
    @Column(name = "RoleId", insertable = false, updatable = false)
    private Integer roleId;

    @Column(name = "CreatedAt")
    private Timestamp createdAt;
}
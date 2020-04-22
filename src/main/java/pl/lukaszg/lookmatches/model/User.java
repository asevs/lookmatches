package pl.lukaszg.lookmatches.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name = "user_active")
    private Boolean active;
    @Column(name = "user_goals")
    private int goals;
    @Column(name = "user_assists")
    private int assists;
    @Column(name = "user_principal_id")
    private String principalId;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_full_name")
    private String fullName;
    @Transient
    private String newPassword;
    @Column(name = "user_photo")
    private String photo;
    @Column(name = "user_created_user_date")
    private LocalDateTime createdUserDate;
    @Column(name = "user_last_login_date")
    private LocalDateTime lastLoginDate;
    @Enumerated(EnumType.STRING)
    private UserLoginType loginType;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_skill", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @OneToOne
    @JsonManagedReference(value = "user-team")
    private Team myTeam;

    @ManyToOne
    @JsonBackReference(value="users-team")
    @JoinColumn(name = "team_id")
    private Team team;

}

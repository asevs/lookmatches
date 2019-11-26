package pl.lukaszg.lookmatches.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@ToString
@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private long id;
    @Column(name = "user_active")
    private int active;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Skill.class)
    @JoinColumn(name="skill_id")
    private Skill nrSkill;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Role.class)
    @JoinColumn(name="role_id")
    private Role nrRole;
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
    @Column(name = "user_new_password")
    private String newPassword;
    @Column(name = "user_photo")
    private String photo;
    @Column(name = "user_created_user_date")
    private LocalDateTime createdUserDate;
    @Column(name = "user_last_login_date")
    private LocalDateTime lastLoginDate;
    @Enumerated(EnumType.STRING)
    private UserLoginType loginType;



//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles;
//
//
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_skill", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
//    private Set<Skill> skills;


}

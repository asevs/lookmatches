package pl.lukaszg.lookmatches.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;
    @Column(name = "team_name")
    @NotNull
    private String name;
    @OneToOne(mappedBy = "myTeam")
    @JsonManagedReference(value = "user-team")
    private User owner;
    @Column(name = "team_goals")
    private int goals;
    @Column(name = "team_assists")
    private int assists;
    @Column(name = "team_slots")
    private int slots;
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "teams", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "users-team")
    private List<User> users;

}

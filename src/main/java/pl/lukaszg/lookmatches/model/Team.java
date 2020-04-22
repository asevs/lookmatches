package pl.lukaszg.lookmatches.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int id;
    @Column(name = "team_name")
    @NotNull
    private String name;
    @OneToOne(mappedBy ="myTeam")
    @JsonBackReference(value="user-team")
    private User owner;
    @Column(name = "team_goals")
    private int goals;
    @Column(name = "team_assists")
    private int assists;
    @OneToMany(mappedBy ="team")
    @JsonManagedReference(value = "users-team")

    private List<User> users;

}

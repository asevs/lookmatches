package pl.lukaszg.lookmatches.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long id;
    @Column(name = "room_price")
    private double price;
    @Column(name = "room_score_team_first")
    private int scoreTeamFirst;
    @Column(name = "room_score_team_second")
    private int scoreTeamSecond;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Skill.class)
    @JoinColumn(name = "room_level_skill")
    private Skill levelSkill;
    @Column(name = "room_created_date")
    private Date createdDate;
    @Column(name = "room_event_date")
    private Date eventDate;

    @ManyToOne
    @JsonBackReference
    private User ownerUser;

    //    private Map <Integer, Integer> assists;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Place.class)
    @JoinColumn(name = "room_place", insertable = false, updatable = false)
    private Place place;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Team.class)
    @JoinColumn(name = "team_second", insertable = false, updatable = false)
    private Team teamSecond;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Team.class)
    @JoinColumn(name = "team_first", insertable = false, updatable = false)
    private Team teamFirst;

    @OneToMany
    @JoinColumn(name = "team_first_users")
    private List<User> teamFirstUsers;

    @OneToMany
    @JoinColumn(name = "team_second_users")
    private List<User> teamSecondUsers;


//    @ElementCollection
//    @MapKeyJoinColumn(name = "user_id")
//    private Map<User, Integer> goals;


}

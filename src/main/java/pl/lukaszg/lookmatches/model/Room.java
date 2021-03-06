package pl.lukaszg.lookmatches.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "room")
public class Room {

    @Enumerated(EnumType.STRING)
    RoomStatus roomStatus;
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
    @Column(name = "slots")
    private int slots;
    @ManyToOne
    @JsonManagedReference(value = "owner-room")
    @JsonIgnore
    private User ownerUser;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Place.class)
    private Place place;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Team.class)
    private Team teamSecond;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Team.class)
    private Team teamFirst;
    @ToString.Exclude
    @ManyToMany(mappedBy = "rooms", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonManagedReference(value = "users-rooms")
    private List<User> users;


//    @ElementCollection
//    @MapKeyJoinColumn(name = "user_id")
//    private Map<User, Integer> goals;


}

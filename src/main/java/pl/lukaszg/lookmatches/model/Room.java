package pl.lukaszg.lookmatches.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "room_id")
    private long id;
    @Column(name = "room_price")
    private double price;
    @Column(name = "room_score_team_first")
    private int scoreTeamFirst;
    @Column(name = "room_score_team_second")
    private int scoreTeamSecond;
    @Column(name = "room_player_1_team_1_goals")
    private int player1Team1Goals;
    @Column(name = "room_room_player_2_team_1_goals")
    private int player2Team1Goals;
    @Column(name = "room_room_player_1_team_2_goals")
    private int player1Team2Goals;
    @Column(name = "room_room_player_2_team_2_goals")
    private int player2Team2Goals;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Skill.class)
    @JoinColumn(name="level_skill")
    private Skill levelSkill;
    @Column(name = "room_created_date")
    private Date createdDate;
    @Column(name = "room_event_date")
    private Date eventDate;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name="owner_user", insertable = false, updatable = false)
    private User ownerUser;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name="player_1_team_1", insertable = false, updatable = false)
    private User player1Team1;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name="player_2_team_1", insertable = false, updatable = false)
    private User player2Team1;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name="player_1_team_2", insertable = false, updatable = false)
    private User player1Team2;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name="player_2_team_2", insertable = false, updatable = false)
    private User player2Team2;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Place.class)
    @JoinColumn(name="place", insertable = false, updatable = false)
    private Place place;
    @Column(name = "room_name_team_second")
    private String nameTeamSecond;
    @Column(name = "room_name_team_first")
    private String nameTeamFirst;











}

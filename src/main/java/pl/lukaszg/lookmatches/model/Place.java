package pl.lukaszg.lookmatches.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "place_id")
    private int id;
    @Column(name = "place_name")
    private String name;
    @Column(name = "place_city")
    private String city;
    @Column(name = "place_street")
    private String street;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name="place_manager")
    private User manager;

}

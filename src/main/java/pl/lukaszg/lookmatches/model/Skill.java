package pl.lukaszg.lookmatches.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "skill_id")
    private int id;
    @Column(name = "skill_name")
    private String skill;

}

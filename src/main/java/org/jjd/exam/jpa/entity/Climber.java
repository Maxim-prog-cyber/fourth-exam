package org.jjd.exam.jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tn_climbers")
@EqualsAndHashCode
@ToString
public class Climber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private int id;

    @Getter
    @Setter
    @Column(name = "fullName", nullable = false, length = 3)
    private String fullName;

    @Getter
    @Setter
    @Column(name = "age", nullable = false)
    private int age;

    @Getter
    @Setter
    @Column(name = "email", nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(name = "UUID", unique = true)
    private UUID uuid;


    @Getter
    @ManyToOne(fetch = FetchType.LAZY)//
    @JoinColumn
    private ClimbingGroup climbingGroup;



}
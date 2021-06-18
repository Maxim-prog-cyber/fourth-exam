package org.jjd.exam.jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_climbing-group")
@EqualsAndHashCode
@ToString
public class ClimbingGroup {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Mountain mountain;

    @Getter
    @Setter
    @OneToMany(mappedBy = "climber", orphanRemoval = true)
    private Climber[] climbers;

    @Getter
    private LocalDateTime start;

    public void setStart() {
        this.start = LocalDateTime.now();
    }

    public ClimbingGroup(Mountain mountain, int climbersCount) {
        this.mountain = mountain;
        climbers = new Climber[climbersCount];


    }

    public void addClimber(Climber climber) {
        for (int i = 0; i < climbers.length; i++) {
            if (climbers[i] == null) {
                climbers[i] = climber;
                return;
            }
        }
        System.out.println("Мест нет");
    }


}
package org.jjd.exam.jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "tb_mountains")
@EqualsAndHashCode
@ToString

public class Mountain implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter
    @Setter
    @Column(name = "name",nullable = false,length = 2)
    private String name;

    @Getter
    @Setter
    @Column(nullable = false)
    private int height;




    public Mountain(){
        this("Гора по умолчанию", 300);
    }

    public Mountain(String name, int height){
        setName(name);
        setHeight(height);
    }

}
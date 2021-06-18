package org.jjd.exam.jpa.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.UUID;

public class JPA {
    public static void main(String[] args) {
        Climber climber1 = new Climber();
        climber1.setAge(10);
        climber1.setFullName("Dim");
        climber1.setEmail("123@mail.ru");
        climber1.setUuid(UUID.randomUUID());

        Climber climber2 = new Climber();
        climber2.setAge(50);
        climber2.setFullName("Petr");
        climber2.setEmail("12345@mail.ru");
        climber2.setUuid(UUID.randomUUID());

        Mountain everest = new Mountain();
        everest.setHeight(9000);
        everest.setName("Everest");

        ClimbingGroup group1 = new ClimbingGroup(everest, 2);

        group1.addClimber(climber1);
        group1.addClimber(climber2);
        group1.setStart();

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("jpaexam");
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();
        manager.persist(climber1);
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        manager.persist(climber2);
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        manager.persist(everest);
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        manager.persist(group1);
        manager.getTransaction().commit();

    }
}
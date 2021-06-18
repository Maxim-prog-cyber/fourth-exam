package org.jjd.exam.jpa.entity;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

public class GroupDao {

    private EntityManager manager;


    public GroupDao(EntityManager manager) {
        this.manager = manager;
    }

    //2.1
    public List<Mountain> getAll() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Mountain> criteriaQuery = builder.createQuery(Mountain.class);
        Root<Mountain> root = criteriaQuery.from(Mountain.class);
        criteriaQuery.select(root);


        TypedQuery<Mountain> typedQuery = manager.createQuery(criteriaQuery);
        List<Mountain> mountains = typedQuery.getResultList();
        return mountains;
    }

    //2.2
    public List<Mountain> getFromMinToMax(int minHeight, int maxHeight) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Mountain> criteriaQuery = builder.createQuery(Mountain.class);
        Root<Mountain> root = criteriaQuery.from(Mountain.class);

        Predicate cond1 = builder.greaterThan(root.get(Mountain_.height), minHeight);
        Predicate cond2 = builder.lessThan(root.get(Mountain_.height), maxHeight);

//        Predicate predicate = builder.between(root.get(Mountain_.height),maxHeight,minHeight);


        Predicate fullCond = builder.and(cond1, cond2);

        criteriaQuery.select(root).where(fullCond);
        TypedQuery<Mountain> typedQuery = manager.createQuery(criteriaQuery);
        List<Mountain> mountains = typedQuery.getResultList();
        return mountains;
    }


    //2.3
    public List<ClimbingGroup> getDidStartClimbing() {
        LocalDateTime currentDataTime = LocalDateTime.now();
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ClimbingGroup> criteriaQuery = builder.createQuery(ClimbingGroup.class);
        Root<ClimbingGroup> root = criteriaQuery.from(ClimbingGroup.class);

        Predicate cond = builder.greaterThan(root.get(ClimbingGroup_.start),currentDataTime);
        criteriaQuery.select(root).where(cond);
        TypedQuery<ClimbingGroup> typedQuery = manager.createQuery(criteriaQuery);
        List<ClimbingGroup> climbingGroups = typedQuery.getResultList();
        return climbingGroups;
    }

    //2.4
    public Climber getByNameAndEmail(String name, String email) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Climber> criteriaQuery = builder.createQuery(Climber.class);
        Root<Climber> root = criteriaQuery.from(Climber.class);

        Predicate cond = builder.equal(root.get(Climber_.fullName), name);
        Predicate cond1 = builder.equal(root.get(Climber_.email), email);
        Predicate fullCond = builder.and(cond, cond1);

        criteriaQuery.select(root).where(fullCond);
        TypedQuery<Climber> typedQuery = manager.createQuery(criteriaQuery);
        Climber climber = typedQuery.getSingleResult();
        return climber;
    }

    //2.5
    public Mountain getByName(String name){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Mountain> criteriaQuery = builder.createQuery(Mountain.class);
        Root<Mountain> root = criteriaQuery.from(Mountain.class);

        Predicate cond = builder.equal(root.get(Mountain_.name), name);

        criteriaQuery.select(root).where(cond);
        TypedQuery<Mountain> typedQuery = manager.createQuery(criteriaQuery);
        Mountain mountain = typedQuery.getSingleResult();
        return mountain;
    }
}
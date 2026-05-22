package ru.netology.netology_hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.netology.netology_hibernate.entity.Person;

import java.util.List;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> getPersonsByCity(String city) {
        String jpql = "SELECT p FROM Person p WHERE p.cityOfLiving = :city";
        TypedQuery<Person> query = entityManager.createQuery(jpql, Person.class);
        query.setParameter("city", city);
        return query.getResultList();
    }
}
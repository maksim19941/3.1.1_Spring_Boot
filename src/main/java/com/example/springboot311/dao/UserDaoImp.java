package com.example.springboot311.dao;


import com.example.springboot311.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getListUser() {
        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }


    @Override
    public void save(String name, String surname, int age) {
        entityManager.persist(new User(name, surname, age));

    }

    @Override
    public void update(User updtUser, long id) {
        User user = entityManager.find(User.class, id);
        user.setAge(updtUser.getAge());
        user.setName(updtUser.getName());
        user.setSurname(updtUser.getSurname());
        entityManager.merge(user);
    }

    @Override
    public void delete(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

}

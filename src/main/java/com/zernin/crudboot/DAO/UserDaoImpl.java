package com.zernin.crudboot.DAO;

import com.zernin.crudboot.model.User;
import org.springframework.stereotype.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where id = :id", User.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(getUserById(id));
    }



    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}

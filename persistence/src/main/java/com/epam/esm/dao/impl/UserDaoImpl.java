package com.epam.esm.dao.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.constant.UserQuery;
import com.epam.esm.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> findByName(String name) {
        return entityManager.createNativeQuery(UserQuery.FIND_BY_NAME_QUERY, User.class).setParameter(1, name)
                .getResultList().stream().findAny();
    }

    @Override
    public List<User> findAll(int page, int size) {
        return entityManager.createNativeQuery(UserQuery.FIND_ALL_QUERY, User.class).setParameter(1, page * size)
                .setParameter(2, size).getResultList();
    }
}

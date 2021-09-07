package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.UserDao;
import com.epam.esm.dao.constant.UserQuery;
import com.epam.esm.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * UserDao implementation.
 *
 * @author Dzmitry Ahinski
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> findAll(int page, int size) {
        return entityManager.createNativeQuery(UserQuery.FIND_ALL_QUERY.getQuery(), User.class).setParameter(1, page * size)
                .setParameter(2, size).getResultList();
    }

    @Override
    public void updateCash(long userId, BigDecimal newCash) {
        entityManager.createNativeQuery(UserQuery.UPDATE_CASH_QUERY.getQuery()).setParameter(1, newCash)
                .setParameter(2, userId).executeUpdate();
    }
}

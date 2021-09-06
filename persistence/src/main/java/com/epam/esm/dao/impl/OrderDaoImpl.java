package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.OrderDao;
import com.epam.esm.dao.constant.OrderQuery;
import com.epam.esm.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * OrderDao implementation.
 *
 * @author Dzmitry Ahinski
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createOrder(long userId, long certificateId) {
        entityManager.createNativeQuery(OrderQuery.BUY_CERTIFICATE_QUERY).setParameter(1, certificateId).setParameter(2, userId)
                .setParameter(3, certificateId).executeUpdate();
    }

    @Override
    public Optional<Order> findById(long id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public List<Order> findAllByUserId(long userId, int page, int size) {
        return entityManager.createNativeQuery(OrderQuery.FIND_ORDERS_BY_USER, Order.class).setParameter(1, userId)
                .setParameter(2, page).setParameter(3, size).getResultList();
    }

    @Override
    public Optional<Order> findByUserAndCertificate(long userId, long certificateId) {
        Query query = entityManager.createNativeQuery(OrderQuery.FIND_ORDER_BY_USER_AND_CERTIFICATE, Order.class);
        query.setParameter(1, userId).setParameter(2, certificateId);
        return query.getResultList().stream().findFirst();
    }
}

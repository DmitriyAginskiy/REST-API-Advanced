package com.epam.esm.dao.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.constant.OrderQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void buyCertificate(long userId, long certificateId) {
        entityManager.createNativeQuery(OrderQuery.BUY_CERTIFICATE_QUERY).setParameter(1, userId).setParameter(2, userId)
                .setParameter(3, certificateId).executeUpdate();
    }
}

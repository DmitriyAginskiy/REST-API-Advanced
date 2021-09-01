package com.epam.esm.service.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.entity.Order;
import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.OperationFailedException;
import com.epam.esm.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional
    @Override
    public void buyCertificate(long userId, long certificateId) {
        Optional<Order> order = orderDao.findByUserAndCertificate(userId, certificateId);
        if(order.isEmpty()) {
            orderDao.buyCertificate(userId, certificateId);
        } else {
            throw new OperationFailedException(certificateId);
        }
    }

    @Override
    public List<Order> findAllByUserId(long userId) {
        return orderDao.findAllByUserId(userId);
    }

    @Override
    public Order findByUserAndCertificate(long userId, long certificateId) {
        return orderDao.findByUserAndCertificate(userId, certificateId).orElseThrow(() -> new ElementSearchException(userId));
    }
}

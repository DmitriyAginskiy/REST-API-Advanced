package com.epam.esm.service.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional
    @Override
    public void buyCertificate(long userId, long certificateId) {
        orderDao.buyCertificate(userId, certificateId);
    }
}

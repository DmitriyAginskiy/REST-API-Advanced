package com.epam.esm.service.impl;

import com.epam.esm.dao.api.GiftCertificateDao;
import com.epam.esm.dao.api.OrderDao;
import com.epam.esm.dao.api.UserDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.OperationNotPerformedException;
import com.epam.esm.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * OrderService implementation.
 *
 * @author Dzmitry Ahinski
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final GiftCertificateDao giftCertificateDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, GiftCertificateDao giftCertificateDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.giftCertificateDao = giftCertificateDao;
    }

    @Transactional
    @Override
    public Order createOrder(long userId, long certificateId) {
        Optional<Order> order = orderDao.findByUserAndCertificate(userId, certificateId);
        if(order.isEmpty()) {
            User user = userDao.findById(userId).orElseThrow(() -> new ElementSearchException(userId));
            GiftCertificate certificate = giftCertificateDao.findById(certificateId).orElseThrow(() -> new ElementSearchException(certificateId));
            if(user.getCash().compareTo(certificate.getPrice()) >= 0) {
                userDao.updateCash(userId, user.getCash().subtract(certificate.getPrice()));
                orderDao.createOrder(userId, certificateId, new Order(certificate.getPrice(), user, certificate));
            }
            return orderDao.findByUserAndCertificate(userId, certificateId).orElseThrow(() -> new OperationNotPerformedException(userId, certificateId));
        } else {
            throw new OperationNotPerformedException(userId, certificateId);
        }
    }

    @Override
    public Order findById(long id) {
        Optional<Order> orderOptional = orderDao.findById(id);
        return orderOptional.orElseThrow(() -> new ElementSearchException(id));
    }

    @Override
    public List<Order> findAllByUserId(long userId, int page, int size) {
        return orderDao.findAllByUserId(userId, page, size);
    }

    @Override
    public Order findByUserAndCertificate(long userId, long certificateId) {
        return orderDao.findByUserAndCertificate(userId, certificateId).orElseThrow(() -> new ElementSearchException(userId, certificateId));
    }
}

package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.OperationNotPerformedException;
import com.epam.esm.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final GiftCertificateDao giftCertificateDao;

    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, GiftCertificateDao giftCertificateDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.giftCertificateDao = giftCertificateDao;
    }

    @Transactional
    @Override
    public Order buyCertificate(long userId, long certificateId) {
        Optional<Order> order = orderDao.findByUserAndCertificate(userId, certificateId);
        if(order.isEmpty()) {
            Optional<User> userOptional = userDao.findById(userId);
            Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findById(certificateId);
            BigDecimal userCash = userOptional.orElseThrow().getCash();
            BigDecimal certificatePrice = giftCertificateOptional.orElseThrow().getPrice();
            if(userCash.compareTo(certificatePrice) >= 0) {
                userDao.updateCash(userId, userCash.subtract(certificatePrice));
                orderDao.buyCertificate(userId, certificateId);
            }
            return orderDao.findByUserAndCertificate(userId, certificateId).orElseThrow(() -> new ElementSearchException(userId));
        } else {
            throw new OperationNotPerformedException(certificateId);
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
        return orderDao.findByUserAndCertificate(userId, certificateId).orElseThrow(() -> new ElementSearchException(userId));
    }
}

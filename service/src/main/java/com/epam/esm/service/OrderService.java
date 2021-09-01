package com.epam.esm.service;

import com.epam.esm.entity.Order;

import java.util.List;

public interface OrderService {

    void buyCertificate(long userId, long certificateId);

    List<Order> findAllByUserId(long userId);

    Order findByUserAndCertificate(long userId, long certificateId);
}

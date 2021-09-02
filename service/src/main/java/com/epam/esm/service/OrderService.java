package com.epam.esm.service;

import com.epam.esm.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order buyCertificate(long userId, long certificateId);

    /**
     * Finds the order by id.
     *
     * @param id of the object to be found.
     * @return found order.
     */
    Order findById(long id);

    List<Order> findAllByUserId(long userId, int page, int size);

    Order findByUserAndCertificate(long userId, long certificateId);
}

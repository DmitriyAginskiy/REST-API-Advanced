package com.epam.esm.service;

import com.epam.esm.entity.Order;

import java.util.List;

public interface OrderService {

    /**
     * Connects certificate with a user.
     *
     * @param userId id of a user.
     * @param certificateId id of a certificate.
     * @return found order.
     */
    Order buyCertificate(long userId, long certificateId);

    /**
     * Finds the order by id.
     *
     * @param id of the object to be found.
     * @return found order.
     */
    Order findById(long id);

    /**
     * Finds all orders by user id.
     *
     * @param userId user id.
     * @param page current page.
     * @param size current search size.
     * @return found order.
     */
    List<Order> findAllByUserId(long userId, int page, int size);

    /**
     * Finds order by user id and certificate id
     *
     * @param userId user id.
     * @param certificateId certificate id
     *
     * @return found order.
     */
    Order findByUserAndCertificate(long userId, long certificateId);
}

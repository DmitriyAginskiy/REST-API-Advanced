package com.epam.esm.dao;

import com.epam.esm.entity.Order;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with orders table.
 *
 * @author Dzmitry Ahinski
 */
public interface OrderDao {

    /**
     * Connects gift certificate with a user.
     *
     * @param userId as user id.
     * @param certificateId as certificate id.
     */
    void buyCertificate(long userId, long certificateId);

    /**
     * Finds all orders by user id.
     *
     * @param userId as user id.
     *
     * @return List of found orders
     */
    List<Order> findAllByUserId(long userId);

    /**
     * Finds order connected with user and certificate.
     *
     * @param userId as user id.
     * @param certificateId as certificate id.
     */
    Optional<Order> findByUserAndCertificate(long userId, long certificateId);
}

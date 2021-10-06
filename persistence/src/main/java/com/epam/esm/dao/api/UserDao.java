package com.epam.esm.dao.api;

import com.epam.esm.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with users table.
 *
 * @author Dzmitry Ahinski
 */
public interface UserDao {

    /**
     * Finds the tag by id.
     *
     * @param id of the object to be found.
     * @return optional object with found user.
     */
    Optional<User> findById(long id);

    /**
     * Finds all the tags.
     *
     * @param page current page for search
     * @param size count of found entities
     *
     * @return list with found users
     */
    List<User> findAll(int page, int size);

    /**
     * Updates user cash.
     *
     * @param newCash new cash amount
     *
     */
    void updateCash(long userId, BigDecimal newCash);
}

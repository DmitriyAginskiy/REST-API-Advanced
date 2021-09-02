package com.epam.esm.service;

import com.epam.esm.entity.User;

import java.util.List;

public interface UserService {

    /**
     * Finds user by id.
     *
     * @param id of the object to be found.
     * @return found user object
     */
    User findById(long id);

    /**
     * Finds all users.
     *
     * @param page current page.
     * @param size current page size.
     * @return list of found users object
     */
    List<User> findAll(int page, int size);
}

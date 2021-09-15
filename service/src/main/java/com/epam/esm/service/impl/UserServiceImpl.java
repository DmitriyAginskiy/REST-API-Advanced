package com.epam.esm.service.impl;

import com.epam.esm.dao.api.UserDao;
import com.epam.esm.entity.User;
import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.util.ServiceMessageManager;
import com.epam.esm.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * UserService implementation.
 *
 * @author Dzmitry Ahinski
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(long id) {
        Optional<User> userOptional = userDao.findById(id);
        return userOptional.orElseThrow(() -> new ElementSearchException(ServiceMessageManager.ELEMENT_SEARCH_KEY.getMessage(id)));
    }

    @Override
    public List<User> findAll(int page, int size) {
        return userDao.findAll(page, size);
    }
}

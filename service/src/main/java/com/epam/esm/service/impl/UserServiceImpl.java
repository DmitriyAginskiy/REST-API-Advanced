package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.User;
import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public User findById(long id) {
        Optional<User> userOptional = userDao.findById(id);
        return userOptional.orElseThrow(() -> new ElementSearchException(id));
    }

    @Override
    public List<User> findAll(int page, int size) {
        return userDao.findAll(page, size);
    }
}

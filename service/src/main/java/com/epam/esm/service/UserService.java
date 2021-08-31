package com.epam.esm.service;

import com.epam.esm.entity.User;

import java.util.List;

public interface UserService {

    User findById(long id);

    List<User> findAll(int page, int size);
}

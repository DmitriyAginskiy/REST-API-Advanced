package com.epam.esm.dao;

import com.epam.esm.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> findById(long id);

    Optional<User> findByName(String name);

    List<User> findAll(int page, int size);
}

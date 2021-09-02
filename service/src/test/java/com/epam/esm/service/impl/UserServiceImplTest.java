package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.User;
import com.epam.esm.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {

    private static User user = new User("SomeName", BigDecimal.valueOf(1000.00));
    private UserService userService;
    @Mock
    private UserDao userDao;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userDao);
    }

    @Test
    void findById() {
        Mockito.when(userDao.findById(1)).thenReturn(Optional.of(user));
        User actual = userService.findById(1);
        assertEquals(user, actual);
    }

    @Test
    void findAll() {
        List<User> expected = new ArrayList<>();
        expected.add(user);
        Mockito.when(userDao.findAll(0, 10)).thenReturn(expected);
        List<User> actual = userService.findAll(0, 10);
        assertEquals(expected, actual);
    }
}
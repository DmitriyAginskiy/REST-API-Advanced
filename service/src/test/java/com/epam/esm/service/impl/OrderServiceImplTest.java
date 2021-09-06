package com.epam.esm.service.impl;

import com.epam.esm.dao.api.GiftCertificateDao;
import com.epam.esm.dao.api.OrderDao;
import com.epam.esm.dao.api.UserDao;
import com.epam.esm.entity.Order;
import com.epam.esm.service.api.OrderService;
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
class OrderServiceImplTest {

    private static Order order = new Order(1, BigDecimal.valueOf(552.0));
    private OrderService orderService;
    @Mock
    private OrderDao orderDao;
    @Mock
    private UserDao userDao;
    @Mock
    private GiftCertificateDao giftCertificateDao;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderServiceImpl(orderDao, userDao, giftCertificateDao);
    }

    @Test
    void findById() {
        Mockito.when(orderDao.findById(1)).thenReturn(Optional.of(order));
        Order actual = orderService.findById(1);
        assertEquals(order, actual);
    }

    @Test
    void findAllByUserId() {
        List<Order> expected = new ArrayList<>();
        expected.add(order);
        Mockito.when(orderDao.findAllByUserId(1, 0, 10)).thenReturn(expected);
        List<Order> actual = orderService.findAllByUserId(1, 0, 10);
        assertEquals(expected, actual);
    }

    @Test
    void findByUserAndCertificate() {
        Mockito.when(orderDao.findByUserAndCertificate(1, 1)).thenReturn(Optional.of(order));
        Order actual = orderService.findByUserAndCertificate(1, 1);
        assertEquals(order, actual);
    }
}
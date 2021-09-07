package com.epam.esm.service.impl;

import com.epam.esm.dao.api.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.api.GiftCertificateService;
import com.epam.esm.validator.GiftCertificateValidator;
import com.epam.esm.validator.TagValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GiftCertificateServiceImplTest {
    private static GiftCertificate giftCertificate = new GiftCertificate(3, "Black widow", "Marvel",
            new BigDecimal("100"), 125, LocalDateTime.of(2019, 9, 11, 15, 32, 12),
            LocalDateTime.of(2021, 3, 11, 15, 32, 12), new HashSet<>());
    private GiftCertificateService service;
    @Mock
    private GiftCertificateDao dao;


    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new GiftCertificateServiceImpl(dao, null, new GiftCertificateValidator(), new TagValidator());
    }

    @Test
    public void findAll() {
        List<GiftCertificate> expected = new ArrayList<>();
        expected.add(giftCertificate);
        Mockito.when(dao.findAll(0, 10)).thenReturn(expected);
        List<GiftCertificate> actual = service.findAll("someName", "somedesc", "DESC", "ASC", new ArrayList<>(), 0, 10);
        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        GiftCertificate expected = giftCertificate;
        Mockito.when(dao.findById(Mockito.anyLong())).thenReturn(Optional.of(expected));
        GiftCertificate actual = service.findById(1);
        assertEquals(expected, actual);
    }
}
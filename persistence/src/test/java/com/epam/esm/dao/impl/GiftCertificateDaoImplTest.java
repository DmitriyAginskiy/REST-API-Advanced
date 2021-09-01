package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.constant.GiftCertificateColumnName;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dao.creator.criteria.impl.SearchCriteria;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GiftCertificateDaoImplTest {

    private static final GiftCertificateDao dao = new GiftCertificateDaoImpl();
    @Autowired
    private EntityManager entityManager = Persistence.createEntityManagerFactory("test").createEntityManager();

    @Test
    void findById() {
        Optional<GiftCertificate> expected = Optional.of(new GiftCertificate(3, "Black widow",
                "Marvel", new BigDecimal("100"), 125, LocalDateTime.of(2019, 9, 11, 15, 32, 12),
                LocalDateTime.of(2021, 3, 11, 15, 32, 12), new HashSet<>()));
        Optional<GiftCertificate> actual = dao.findById(3);
        assertEquals(expected, actual);
    }

    @Test
    void findAllByCriteria() {
        GiftCertificate expected = new GiftCertificate(3, "Black widow", "Marvel",
                new BigDecimal("100"), 125, LocalDateTime.of(2019, 9, 11, 15, 32, 12),
                LocalDateTime.of(2021, 3, 11, 15, 32, 12), new HashSet<>());
        List<Criteria> criteriaList = new ArrayList<>();
        criteriaList.add(new SearchCriteria(GiftCertificateColumnName.DESCRIPTION, "Mar"));
        List<GiftCertificate> actual = dao.findAllByCriteria(criteriaList);
        assertEquals(expected, actual.get(0));
    }

    @Test
    void insert() {
        String[] names = { "Victor", "Nikita", "Pavel", "Dmitriy", "Oleg", "Nastya", "Mike", "Alex", "Helga",
                           "Valeria", "Ihor", "Elena", "Anna", "Tanya", "Marina" };
        String[] surnames = { "Pavlo", "Gurbo", "Nikito", "Mordo", "Vanin", "Lyfar", "Pinchuk",
                              "Galua", "Delacrua", "Marua", "Eria", "Eredia"};
        Random random = new Random();
        for(int i = 0; i < 1200; i++) {
            String str = new String(names[random.nextInt(names.length)] + " " + surnames[random.nextInt(surnames.length)]);
            User user = new User(str);
            entityManager.persist(user);
        }
        assertTrue(true);
    }
}
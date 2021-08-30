package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.GiftCertificateQuery;
import com.epam.esm.dao.creator.GiftCertificateQueryCreator;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * GiftCertificateDao implementation.
 *
 * @author Dzmitry Ahinski
 */
@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public GiftCertificateDaoImpl() {
    }

    @Override
    public void insert(GiftCertificate certificate) {
        entityManager.persist(certificate);
    }

    @Override
    public void delete(GiftCertificate giftCertificate) {
        entityManager.remove(giftCertificate);
    }

    @Override
    public void update(GiftCertificate certificate) {
        entityManager.merge(certificate);
    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        return Optional.ofNullable(entityManager.find(GiftCertificate.class, id));
    }

    @Override
    public List<GiftCertificate> findAll(int page, int size) {
        return entityManager.createNativeQuery(GiftCertificateQuery.FIND_ALL_QUERY, GiftCertificate.class)
                .setParameter(1, page * size).setParameter(2, size).getResultList();
    }

    @Override
    public List<GiftCertificate> findAllByCriteria(List<Criteria> criteriaList) {
        String query = GiftCertificateQueryCreator.createSearchQuery(criteriaList);
        return entityManager.createNativeQuery(query, GiftCertificate.class).getResultList();
    }
}

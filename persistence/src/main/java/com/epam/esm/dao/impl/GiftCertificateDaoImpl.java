package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.GiftCertificateColumnName;
import com.epam.esm.dao.constant.GiftCertificateQuery;
import com.epam.esm.dao.creator.FieldCondition;
import com.epam.esm.dao.creator.GiftCertificateQueryCreator;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    private final GiftCertificateMapper mapper;

    @Autowired
    public GiftCertificateDaoImpl(GiftCertificateMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Optional<GiftCertificate> insert(GiftCertificate certificate) throws DaoException {
        Query query = entityManager.createNativeQuery(GiftCertificateQuery.INSERT_GIFT_CERTIFICATE_QUERY);
        query.setParameter(1, certificate.getName());
        query.setParameter(2, certificate.getDescription());
        query.setParameter(3, certificate.getPrice());
        query.setParameter(4, certificate.getDuration());
        query.setParameter(5, Timestamp.valueOf(certificate.getCreateDate()));
        query.setParameter(6, Timestamp.valueOf(certificate.getLastUpdateDate()));
        int result = query.executeUpdate();
        if (result > 0) {
            if (certificate.getTags() != null && !certificate.getTags().isEmpty()) {
                updateCertificateTags(certificate.getId(), certificate.getTags());
            }
            return Optional.of(certificate);
        } else {
            throw new DaoException("Element with id " + certificate.getId() + " was not inserted!");
        }
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public boolean removeTagsFromCertificate(long id) {
        return false;
    }

    @Override
    public void update(long id, List<FieldCondition> conditionList) throws DaoException {

    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        return Optional.of(new GiftCertificate());
    }

    @Override
    public List<GiftCertificate> findAll() {
        return entityManager.createNativeQuery(GiftCertificateQuery.FIND_ALL_QUERY, GiftCertificate.class).getResultList();
    }

    @Override
    public List<GiftCertificate> findAllByCriteria(List<Criteria> criteriaList) {
        String query = GiftCertificateQueryCreator.createSearchQuery(criteriaList);
        return new ArrayList<>();
    }

    @Override
    public boolean updateCertificateTags(long id, List<Tag> tags) {
        return false;
    }
}

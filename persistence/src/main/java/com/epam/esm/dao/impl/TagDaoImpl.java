package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.constant.TagQuery;
import com.epam.esm.dao.creator.TagQueryCreator;
import com.epam.esm.dao.mapper.TagMapper;
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
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TagDao implementation.
 *
 * @author Dzmitry Ahinski
 */
@Repository
public class TagDaoImpl implements TagDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TagDaoImpl() {
    }

    @Override
    public void insert(Tag tag) {
        return entityManager.persist(tag);
    }

    @Override
    public void delete(long id) {
    }

    @Override
    public Optional<Tag> findById(long id) {
        return Optional.of(new Tag());
    }

    @Override
    public List<Tag> findByCertificate(long certificateId) {
        return entityManager.createNativeQuery(TagQuery.FIND_TAGS_BY_CERTIFICATE, Tag.class)
                .setParameter(1, certificateId)
                .getResultList();
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return Optional.of(new Tag());
    }

    @Override
    public List<Tag> findAll() {
        return new ArrayList<>();
    }

    @Override
    public List<Tag> findAllExisting(List<Tag> tags) {
        String query = TagQueryCreator.createExistingTagsSelectionQuery(tags.size());
        Query nativeQuery = entityManager.createNativeQuery(query, Tag.class);
        int counter = 1;
        for(Tag tag : tags) {
            nativeQuery.setParameter(counter++, tag.getName());
        }
        return nativeQuery.getResultList();
    }

    @Override
    public void disconnectTagFromCertificates(long id) {
    }
}

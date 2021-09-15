package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.TagDao;
import com.epam.esm.dao.constant.TagQuery;
import com.epam.esm.dao.creator.TagQueryCreator;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.dao.exception.util.DaoMessageManager;
import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Override
    public void insert(Tag tag) throws DaoException {
        Optional<Tag> tagOptional = findByName(tag.getName());
        if(tagOptional.isPresent()) {
            entityManager.persist(tag);
        } else {
            throw new DaoException(DaoMessageManager.CAN_NOT_PERSIST.getMessage(tag));
        }
    }

    @Override
    public void delete(long id) throws DaoException {
        Optional<Tag> tagOptional = findById(id);
        if(tagOptional.isPresent()) {
            disconnectTagFromCertificates(id);
            entityManager.remove(tagOptional.get());
        } else {
            throw new DaoException(DaoMessageManager.CAN_NOT_DELETE.getMessage(id));
        }
    }

    @Override
    public Optional<Tag> findById(long id) {
        return Optional.ofNullable(entityManager.find(Tag.class, id));
    }

    @Override
    public Optional<Tag> findByName(String name) {
       Query query = entityManager.createNativeQuery(TagQuery.FIND_BY_NAME.getQuery(), Tag.class);
       query = query.setParameter(1, name);
       return query.getResultList().stream().findFirst();
    }

    @Override
    public List<Tag> findAll(int page, int size) {
        return entityManager.createNativeQuery(TagQuery.FIND_ALL_TAGS.getQuery(), Tag.class)
                .setParameter(1, page * size).setParameter(2, size).getResultList();
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

    private void disconnectTagFromCertificates(long id) {
        entityManager.createNativeQuery(TagQuery.DISCONNECT_TAG_FROM_CERTIFICATES.getQuery()).setParameter(1, id).executeUpdate();
    }

    @Override
    public Optional<Tag> findMostExpensiveTag() {
        return entityManager.createNativeQuery(TagQuery.FIND_MOST_EXPENSIVE_TAG.getQuery(), Tag.class)
                .getResultList().stream().findFirst();
    }
}

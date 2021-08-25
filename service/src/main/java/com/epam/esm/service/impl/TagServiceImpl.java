package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoException;
import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.InvalidFieldException;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Optional;

/**
 * TagService implementation.
 *
 * @author Dzmitry Ahinski
 */
@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Transactional
    @Override
    public Tag insert(Tag tag) {
        Optional<Tag> tagOptional = tagDao.findByName(tag.getName());
        if(TagValidator.isNameValid(tag.getName()) && tagOptional.isEmpty()) {
            try {
                long id = tagDao.insert(tag);
                return tagDao.findById(id).orElseThrow(() -> new ElementSearchException(id));
            } catch (DaoException e) {
                throw new ElementSearchException(tag.getId());
            }
        } else {
            throw new InvalidFieldException(tag.getId());
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        Optional<Tag> tagOptional = tagDao.findById(id);
        if(tagOptional.isPresent()) {
            tagDao.disconnectTagFromCertificates(id);
            tagDao.delete(id);
        } else {
            throw new ElementSearchException(id);
        }
    }

    @Override
    public Tag findById(long id) {
        Optional<Tag> tagOptional = tagDao.findById(id);
        if(tagOptional.isPresent()) {
            return tagOptional.get();
        } else {
            throw new ElementSearchException(id);
        }
    }

    @Override
    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    @Override
    public List<Tag> findAllExisting(List<Tag> tags) {
        return tagDao.findAllExisting(tags);
    }
}

package com.epam.esm.service.impl;

import com.epam.esm.dao.api.TagDao;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.OperationNotPerformedException;
import com.epam.esm.exception.util.ServiceMessageManager;
import com.epam.esm.service.api.TagService;
import com.epam.esm.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final TagValidator tagValidator;

    @Autowired
    public TagServiceImpl(TagDao tagDao, TagValidator tagValidator) {
        this.tagDao = tagDao;
        this.tagValidator = tagValidator;
    }

    @Transactional
    @Override
    public Tag insert(Tag tag) {
        if(tagValidator.isNameValid(tag.getName())) {
            try {
                tagDao.insert(tag);
            } catch (DaoException exception) {
                throw new OperationNotPerformedException(exception.getMessage());
            }
            return tag;
        } else {
            throw new OperationNotPerformedException(ServiceMessageManager.OPERATION_NOT_PERFORMED.getMessage(tag.getName()));
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        try {
            tagDao.delete(id);
        } catch (DaoException exception) {
            throw new OperationNotPerformedException(exception.getMessage());
        }
    }

    @Override
    public Tag findById(long id) {
        Optional<Tag> tagOptional = tagDao.findById(id);
        return tagOptional.orElseThrow(() -> new ElementSearchException(ServiceMessageManager.OPERATION_NOT_PERFORMED.getMessage(id)));
    }

    @Override
    public List<Tag> findAll(int page, int size) {
        return tagDao.findAll(page, size);
    }

    @Override
    public List<Tag> findAllExisting(List<Tag> tags) {
        return tagDao.findAllExisting(tags);
    }

    @Override
    public Tag findMostExpensiveTag() {
        return tagDao.findMostExpensiveTag().orElseThrow(() -> new ElementSearchException(ServiceMessageManager.ELEMENT_SEARCH_KEY.getMessage()));
    }
}

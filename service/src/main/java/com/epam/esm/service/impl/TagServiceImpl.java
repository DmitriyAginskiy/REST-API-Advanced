package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.OperationFailedException;
import com.epam.esm.service.TagService;
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

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Transactional
    @Override
    public Tag insert(Tag tag) {
        Optional<Tag> tagOptional = tagDao.findByName(tag.getName());
        if(TagValidator.isNameValid(tag.getName()) && tagOptional.isEmpty()) {
            tagDao.insert(tag);
            return tagDao.findById(tag.getId()).orElseThrow(() -> new OperationFailedException(tag.getId()));
        } else {
            throw new OperationFailedException(tag.getId());
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        Optional<Tag> tagOptional = tagDao.findById(id);
        tagDao.disconnectTagFromCertificates(id);
        tagDao.delete(tagOptional.orElseThrow(() -> new ElementSearchException(id)));
    }

    @Override
    public Tag findById(long id) {
        Optional<Tag> tagOptional = tagDao.findById(id);
        return tagOptional.orElseThrow(() -> new ElementSearchException(id));
    }

    @Override
    public List<Tag> findAll(int page, int size) {
        return tagDao.findAll(page, size);
    }

    @Override
    public List<Tag> findAllExisting(List<Tag> tags) {
        return tagDao.findAllExisting(tags);
    }
}

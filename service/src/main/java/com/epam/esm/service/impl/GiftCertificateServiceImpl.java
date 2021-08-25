package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.creator.FieldCondition;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoException;
import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.InvalidFieldException;
import com.epam.esm.service.CertificateConditionStrategy;
import com.epam.esm.service.CriteriaStrategy;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.epam.esm.validator.GiftCertificateValidator;

/**
 * GiftCertificateService implementation.
 *
 * @author Dzmitry Ahinski
 */
@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao certificateDao;
    private final TagService tagService;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao certificateDao, TagService tagService) {
        this.certificateDao = certificateDao;
        this.tagService = tagService;
    }

    @Transactional
    @Override
    public GiftCertificate insert(GiftCertificate certificate) {
        if(certificate != null && GiftCertificateValidator.areValidFields(certificate)) {
            LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            certificate.setCreateDate(dateTime);
            certificate.setLastUpdateDate(dateTime);
            if(certificate.getTags() != null) {
                HashSet<Tag> tagsWithoutDuplicates = new HashSet<>(certificate.getTags());
                List<Tag> existingTags = tagService.findAllExisting(certificate.getTags());
                List<Tag> newTags = tagsWithoutDuplicates.stream().filter(t -> !existingTags
                        .contains(t)).collect(Collectors.toList());
                newTags.addAll(existingTags);
                certificate.setTags(newTags);
            }
            try {
                certificateDao.insert(certificate);
                Optional<GiftCertificate> certificateOptional = certificateDao.findById(certificate.getId());
                return certificateOptional.orElseThrow(() -> new ElementSearchException(certificate.getId()));
            } catch (DaoException e) {
                throw new ElementSearchException(certificate.getId());
            }
        } else {
            throw new InvalidFieldException(certificate.getId());
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        Optional<GiftCertificate> giftCertificateOptional = certificateDao.findById(id);
        if(giftCertificateOptional.isPresent()) {
            certificateDao.delete(giftCertificateOptional.get());
        } else {
            throw new ElementSearchException(id);
        }
    }

    @Transactional
    @Override
    public GiftCertificate update(long id, GiftCertificate certificate) {
        if(id == certificate.getId()) {
            Optional<GiftCertificate> giftCertificateOptional = certificateDao.findById(id);
            if(giftCertificateOptional.isPresent()) {
                List<FieldCondition> conditionList = CertificateConditionStrategy.createConditionsList(certificate);
                try {
                    certificateDao.update(id, conditionList);
                } catch (DaoException e) {
                    throw new ElementSearchException(id);
                }
                if(GiftCertificateValidator.areTagsValid(certificate.getTags())) {
                    certificateDao.removeTagsFromCertificate(id);
                    HashSet<Tag> tagsWithoutDuplicates = new HashSet<>(certificate.getTags());
                    List<Tag> existingTags = tagService.findAllExisting(certificate.getTags());
                    List<Tag> newTags = tagsWithoutDuplicates.stream().filter(t -> !existingTags
                            .contains(t)).collect(Collectors.toList());
                    if(newTags.size() > 0) {
                        newTags.forEach(tagService::insert);
                    }
                    certificateDao.updateCertificateTags(id, tagService.findAllExisting(certificate.getTags()));
                }
                return certificateDao.findById(id).orElseThrow(() -> new ElementSearchException(id));
            } else {
                throw new ElementSearchException(id);
            }
        } else {
            throw new InvalidFieldException(certificate.getId());
        }
    }

    @Override
    public GiftCertificate findById(long id) {
        Optional<GiftCertificate> certificate = certificateDao.findById(id);
        if(certificate.isEmpty()) {
            throw new ElementSearchException(id);
        }
        return certificate.orElseThrow(() -> new ElementSearchException(id));
    }

    @Override
    public List<GiftCertificate> findAll(String certificateName, String tagName, String description, String sortByDate, String sortByName) {
        List<Criteria> criteriaList = new ArrayList<>();
        String[] criteriaArray = new String[] { certificateName, tagName, description, sortByDate, sortByName };
        int counter = 0;
        for(CriteriaStrategy criteriaStrategy : CriteriaStrategy.values()) {
            Optional<Criteria> criteriaOptional = criteriaStrategy.createCriteria(criteriaArray[counter++]);
            criteriaOptional.ifPresent(criteriaList::add);
        }
        if(criteriaList.isEmpty()) {
            return certificateDao.findAll();
        } else {
            return certificateDao.findAllByCriteria(criteriaList);
        }
    }
}

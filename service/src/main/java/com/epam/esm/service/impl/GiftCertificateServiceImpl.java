package com.epam.esm.service.impl;

import com.epam.esm.dao.api.GiftCertificateDao;
import com.epam.esm.dao.constant.TagColumnName;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dao.creator.criteria.impl.SearchCriteria;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.OperationNotPerformedException;
import com.epam.esm.exception.util.ServiceMessageManager;
import com.epam.esm.service.CertificateConditionStrategy;
import com.epam.esm.service.CriteriaStrategy;
import com.epam.esm.service.api.GiftCertificateService;
import com.epam.esm.service.api.TagService;
import com.epam.esm.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
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
    private final GiftCertificateValidator giftCertificateValidator;
    private final TagValidator tagValidator;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao certificateDao, TagService tagService,
                                      GiftCertificateValidator giftCertificateValidator, TagValidator tagValidator) {
        this.certificateDao = certificateDao;
        this.tagService = tagService;
        this.giftCertificateValidator = giftCertificateValidator;
        this.tagValidator = tagValidator;
    }

    @Transactional
    @Override
    public GiftCertificate insert(GiftCertificate certificate) {
        if(certificate != null && giftCertificateValidator.isNameValid(certificate.getName())
                && giftCertificateValidator.isDescriptionValid(certificate.getDescription())
                && giftCertificateValidator.isPriceValid(certificate.getPrice())
                && giftCertificateValidator.isDurationValid(certificate.getDuration())) {
            if(certificate.getTags() != null && !certificate.getTags().isEmpty()) {
                HashSet<Tag> tagsWithoutDuplicates = new HashSet<>(certificate.getTags());
                List<Tag> existingTags = tagService.findAllExisting(new ArrayList<>(certificate.getTags()));
                List<Tag> newTags = tagsWithoutDuplicates.stream().filter(t -> !existingTags
                        .contains(t)).collect(Collectors.toList());
                newTags.addAll(existingTags);
                certificate.setTags(new HashSet<>(newTags));
            }
            certificateDao.insert(certificate);
            Optional<GiftCertificate> certificateOptional = certificateDao.findById(certificate.getId());
            return certificateOptional.orElseThrow(() -> new OperationNotPerformedException(
                    ServiceMessageManager.OPERATION_NOT_PERFORMED.getMessage(certificate)
            ));
        } else {
            throw new OperationNotPerformedException(ServiceMessageManager.OPERATION_NOT_PERFORMED.getMessage(certificate));
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        try {
            certificateDao.delete(id);
        } catch (DaoException exception) {
            throw new OperationNotPerformedException(ServiceMessageManager.OPERATION_NOT_PERFORMED.getMessage(exception.getMessage()));
        }
    }

    @Transactional
    @Override
    public GiftCertificate update(long id, GiftCertificate certificate) {
        Optional<GiftCertificate> giftCertificateOptional = certificateDao.findById(id);
        GiftCertificate newCertificate = giftCertificateOptional.orElseThrow(() -> new ElementSearchException(
                ServiceMessageManager.ELEMENT_SEARCH_KEY.getMessage(id)
        ));
        if(certificate.getTags() != null) {
            HashSet<Tag> tagsWithoutDuplicates = new HashSet<>(certificate.getTags());
            List<Tag> existingTags = tagService.findAllExisting(new ArrayList<>(certificate.getTags()));
            List<Tag> newTags = tagsWithoutDuplicates.stream().filter(t -> !existingTags
                    .contains(t)).collect(Collectors.toList());
            newTags.addAll(existingTags);
            newCertificate.setTags(new HashSet<>(newTags));
        }
        CertificateConditionStrategy.updateCertificate(newCertificate, certificate);
        certificateDao.update(newCertificate);
        return newCertificate;
    }

    @Override
    public GiftCertificate findById(long id) {
        Optional<GiftCertificate> certificate = certificateDao.findById(id);
        return certificate.orElseThrow(() -> new ElementSearchException(ServiceMessageManager.ELEMENT_SEARCH_KEY.getMessage(id)));
    }

    @Override
    public List<GiftCertificate> findAll(String certificateName, String description, String sortByDate, String sortByName,
                                         List<String> tagName, int page, int size) {
        List<Criteria> criteriaList = new ArrayList<>();
        List<String> criteriaForValidation = Arrays.asList(certificateName, description, sortByDate, sortByName);
        int counter = 0;
        for(CriteriaStrategy criteriaStrategy : CriteriaStrategy.values()) {
            Optional<Criteria> criteriaOptional = criteriaStrategy.createCriteria(criteriaForValidation.get(counter++));
            criteriaOptional.ifPresent(criteriaList::add);
        }
        if(tagName != null && !tagName.isEmpty()) {
            List<String> validTagNames = tagName.stream().filter(tagValidator::isNameValid).collect(Collectors.toList());
            validTagNames.forEach(tag -> criteriaList.add(new SearchCriteria(TagColumnName.TAG_NAME.getColumnName(), tag)));
        }
        if(criteriaList.isEmpty()) {
            return certificateDao.findAll(page, size);
        } else {
            return certificateDao.findAllByCriteria(criteriaList);
        }
    }
}

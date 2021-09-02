package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.TagColumnName;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dao.creator.criteria.impl.SearchCriteria;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.OperationNotPerformedException;
import com.epam.esm.service.CertificateConditionStrategy;
import com.epam.esm.service.CriteriaStrategy;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                List<Tag> existingTags = tagService.findAllExisting(new ArrayList<>(certificate.getTags()));
                List<Tag> newTags = tagsWithoutDuplicates.stream().filter(t -> !existingTags
                        .contains(t)).collect(Collectors.toList());
                newTags.addAll(existingTags);
                certificate.setTags(new HashSet<>(newTags));
            }
            certificateDao.insert(certificate);
            Optional<GiftCertificate> certificateOptional = certificateDao.findById(certificate.getId());
            return certificateOptional.orElseThrow(() -> new OperationNotPerformedException(certificate.getId()));
        } else {
            throw new OperationNotPerformedException(certificate.getId());
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
        Optional<GiftCertificate> giftCertificateOptional = certificateDao.findById(id);
        GiftCertificate newCertificate = giftCertificateOptional.orElseThrow(() -> new ElementSearchException(id));
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
        return certificate.orElseThrow(() -> new ElementSearchException(id));
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
            List<String> validTagNames = tagName.stream().filter(TagValidator::isNameValid).collect(Collectors.toList());
            validTagNames.forEach(tag -> criteriaList.add(new SearchCriteria(TagColumnName.TAG_NAME, tag)));
        }
        if(criteriaList.isEmpty()) {
            return certificateDao.findAll(page, size);
        } else {
            return certificateDao.findAllByCriteria(criteriaList);
        }
    }
}

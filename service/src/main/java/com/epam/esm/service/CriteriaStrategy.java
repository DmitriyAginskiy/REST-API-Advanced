package com.epam.esm.service;

import com.epam.esm.dao.constant.GiftCertificateColumnName;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dao.creator.criteria.impl.SearchCriteria;
import com.epam.esm.dao.creator.criteria.impl.SortCriteria;
import com.epam.esm.validator.GiftCertificateValidator;

import java.util.Optional;

/**
 * Enum contains methods for creating criteria.
 *
 * @author Dzmitry Ahinski
 */
public enum CriteriaStrategy {

    CERTIFICATE_NAME {
        @Override
        public Optional<Criteria> createCriteria(String criteria) {
            if(giftCertificateValidator.isNameValid(criteria)) {
                return Optional.of(new SearchCriteria(GiftCertificateColumnName.NAME.getColumnName(), criteria));
            }
            return Optional.empty();
        }
    },
    DESCRIPTION {
        @Override
        public Optional<Criteria> createCriteria(String criteria) {
            if(giftCertificateValidator.isDescriptionValid(criteria)) {
                return Optional.of(new SearchCriteria(GiftCertificateColumnName.DESCRIPTION.getColumnName(), criteria));
            }
            return Optional.empty();
        }
    },
    SORT_BY_DATE {
        @Override
        public Optional<Criteria> createCriteria(String criteria) {
            if(criteria != null && (criteria.equalsIgnoreCase(SortCriteria.SORT_ASC)
                    || criteria.equalsIgnoreCase(SortCriteria.SORT_DESC))) {
                String sortType = criteria.equalsIgnoreCase(SortCriteria.SORT_ASC) ? SortCriteria.SORT_ASC : SortCriteria.SORT_DESC;
                return Optional.of(new SortCriteria(GiftCertificateColumnName.CREATE_DATE.getColumnName(), sortType));
            }
            return Optional.empty();
        }
    },
    SORT_BY_NAME {
        @Override
        public Optional<Criteria> createCriteria(String criteria) {
            if(criteria != null && (criteria.equalsIgnoreCase(SortCriteria.SORT_ASC)
                    || criteria.equalsIgnoreCase(SortCriteria.SORT_DESC))) {
                String sortType = criteria.equalsIgnoreCase(SortCriteria.SORT_ASC) ? SortCriteria.SORT_ASC : SortCriteria.SORT_DESC;
                return Optional.of(new SortCriteria(GiftCertificateColumnName.NAME.getColumnName(), sortType));
            }
            return Optional.empty();
        }
    };

    private static final GiftCertificateValidator giftCertificateValidator = new GiftCertificateValidator();

    /**
     * Creates criteria object from String.
     *
     * @param criteria as String, which contains search criteria
     * @return Optional object with created criteria
     */
    public abstract Optional<Criteria> createCriteria(String criteria);
}

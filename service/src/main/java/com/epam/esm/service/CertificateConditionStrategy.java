package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.validator.GiftCertificateValidator;

/**
 * Enum contains methods for certificate fields.
 *
 * @author Dzmitry Ahinski
 */
public enum CertificateConditionStrategy {

    NAME {
        @Override
        public void updateCondition(GiftCertificate newCertificate, GiftCertificate certificate) {
            if(!newCertificate.getName().equals(certificate.getName())
                    && GiftCertificateValidator.isNameValid(certificate.getName())) {
                newCertificate.setName(certificate.getName());
            }
        }
    },
    DESCRIPTION {
        @Override
        public void updateCondition(GiftCertificate newCertificate, GiftCertificate certificate) {
            if(!newCertificate.getDescription().equals(certificate.getDescription())
                    && GiftCertificateValidator.isDescriptionValid(certificate.getDescription())) {
                newCertificate.setDescription(certificate.getDescription());
            }
        }
    },
    PRICE {
        @Override
        public void updateCondition(GiftCertificate newCertificate, GiftCertificate certificate) {
            if(!newCertificate.getPrice().equals(certificate.getPrice())
                    && GiftCertificateValidator.isPriceValid(certificate.getPrice())) {
                newCertificate.setPrice(certificate.getPrice());
            }
        }
    },
    DURATION {
        @Override
        public void updateCondition(GiftCertificate newCertificate, GiftCertificate certificate) {
            if(newCertificate.getDuration() != certificate.getDuration() && GiftCertificateValidator.isNameValid(certificate.getName())) {
                newCertificate.setDuration(certificate.getDuration());
            }
        }
    };

    /**
     * Adds new field condition to the list.
     *
     * @param newCertificate as GiftCertificate object
     * @param certificate as GiftCertificate object
     */
    public abstract void updateCondition(GiftCertificate newCertificate, GiftCertificate certificate);

    /**
     * Creates list with FieldCondition objects.
     *
     * @param newCertificate as GiftCertificate object
     * @param certificate as GiftCertificate object
     */
    public static void updateCertificate(GiftCertificate newCertificate, GiftCertificate certificate) {
        for(CertificateConditionStrategy conditionStrategy : CertificateConditionStrategy.values()) {
            conditionStrategy.updateCondition(newCertificate, certificate);
        }
    }
}

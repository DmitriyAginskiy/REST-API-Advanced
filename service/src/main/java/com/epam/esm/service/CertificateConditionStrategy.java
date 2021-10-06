package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.validator.GiftCertificateValidator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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
                    && giftCertificateValidator.isNameValid(certificate.getName())) {
                newCertificate.setName(certificate.getName());
            }
        }
    },
    DESCRIPTION {
        @Override
        public void updateCondition(GiftCertificate newCertificate, GiftCertificate certificate) {
            if(!newCertificate.getDescription().equals(certificate.getDescription())
                    && giftCertificateValidator.isDescriptionValid(certificate.getDescription())) {
                newCertificate.setDescription(certificate.getDescription());
            }
        }
    },
    PRICE {
        @Override
        public void updateCondition(GiftCertificate newCertificate, GiftCertificate certificate) {
            if(!newCertificate.getPrice().equals(certificate.getPrice())
                    && giftCertificateValidator.isPriceValid(certificate.getPrice())) {
                newCertificate.setPrice(certificate.getPrice().setScale(2, RoundingMode.HALF_UP));
            }
        }
    },
    DURATION {
        @Override
        public void updateCondition(GiftCertificate newCertificate, GiftCertificate certificate) {
            if(newCertificate.getDuration() != certificate.getDuration() && giftCertificateValidator.isDurationValid(certificate.getDuration())) {
                newCertificate.setDuration(certificate.getDuration());
            }
        }
    };

    private static final GiftCertificateValidator giftCertificateValidator = new GiftCertificateValidator();

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

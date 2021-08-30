package com.epam.esm.dao;

import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.entity.GiftCertificate;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with gift certificates table.
 *
 * @author Dzmitry Ahinski
 */
public interface GiftCertificateDao {

    /**
     * Adds gift certificate to the table.
     *
     * @param certificate object to be added.
     * @return long value with created object id.
     * @throws DaoException if object is not inserted
     */
    void insert(GiftCertificate certificate);

    /**
     * Deletes gift certificate from the table.
     *
     * @param id of the object to be deleted.
     *
     */
    void delete(GiftCertificate giftCertificate);

    /**
     * Updates the certificate.
     *
     * @param id of the object to be updated.
     * @param conditionList with fields to be updated.
     * @throws DaoException if resources does not exist
     */
    void update(GiftCertificate certificate);

    /**
     * Finds the certificate by id.
     *
     * @param id of the object to be found.
     * @return optional object with found certificate.
     */
    Optional<GiftCertificate> findById(long id);

    /**
     * Finds all the certificates.
     *
     * @return list with found certificates
     */
    List<GiftCertificate> findAll();

    /**
     * Finds all the certificates by search and sort criteria.
     *
     * @param criteriaList list of criteria
     * @return list with found certificates.
     */
    List<GiftCertificate> findAllByCriteria(List<Criteria> criteriaList);
}

package com.epam.esm.dao.api;

import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dao.exception.DaoException;
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
     *
     */
    void insert(GiftCertificate certificate);

    /**
     * Deletes gift certificate from the table.
     *
     * @param id of object to be deleted.
     * @throws DaoException if the element does not exist
     *
     */
    void delete(long id) throws DaoException;

    /**
     * Updates the certificate.
     *
     * @param certificate object to be updated.
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
    List<GiftCertificate> findAll(int page, int size);

    /**
     * Finds all the certificates by search and sort criteria.
     *
     * @param criteriaList list of criteria
     * @return list with found certificates.
     */
    List<GiftCertificate> findAllByCriteria(List<Criteria> criteriaList, int page, int size);
}

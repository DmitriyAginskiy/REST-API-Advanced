package com.epam.esm.dao.constant;

/**
 * Class with the gift certificates queries.
 *
 * @author Dzmitry Ahinski
 */
public final class GiftCertificateQuery {

    public static final String FIND_ALL_QUERY = "SELECT gift_certificates.*, tags.* FROM gift_certificates LEFT JOIN "
            + "gift_certificates_has_tags ON certificate_id = gift_certificates_id_fk LEFT JOIN tags ON tag_id = tags_id_fk GROUP BY certificate_id LIMIT ?, ?;";

    private GiftCertificateQuery() {

    }
}

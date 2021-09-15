package com.epam.esm.dao.constant;

/**
 * Enum with the gift certificates queries.
 *
 * @author Dzmitry Ahinski
 */
public enum GiftCertificateQuery {

    FIND_ALL_QUERY("SELECT gift_certificates.*, tags.* FROM gift_certificates LEFT JOIN "
            + "gift_certificates_has_tags ON certificate_id = gift_certificates_id_fk LEFT JOIN tags ON tag_id = tags_id_fk GROUP BY certificate_id LIMIT ?, ?;"),
    REMOVE_TAGS_FROM_CERTIFICATE("DELETE FROM gift_certificates_has_tags WHERE gift_certificates_id_fk = ?;");

    private String query;

    GiftCertificateQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

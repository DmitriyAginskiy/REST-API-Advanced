package com.epam.esm.dao.constant;

/**
 * Enum with the tags queries.
 *
 * @author Dzmitry Ahinski
 */
public enum TagQuery {

    FIND_ALL_TAGS("SELECT * FROM tags LIMIT ?, ?;"),
    FIND_BY_NAME("SELECT * FROM tags WHERE tag_name = ?;"),
    DISCONNECT_TAG_FROM_CERTIFICATES("DELETE FROM gift_certificates_has_tags WHERE tags_id_fk = ?;"),
    FIND_MOST_EXPENSIVE_TAG(new StringBuilder("SELECT tags.* FROM users")
    .append(" INNER JOIN orders ON users.user_id = orders.users_id_fk")
    .append(" INNER JOIN gift_certificates ON orders.gift_certificates_id_fk = gift_certificates.certificate_id")
    .append(" INNER JOIN gift_certificates_has_tags ON gift_certificates.certificate_id = gift_certificates_has_tags.gift_certificates_id_fk")
    .append(" INNER JOIN tags ON gift_certificates_has_tags.tags_id_fk = tags.tag_id")
    .append(" GROUP BY users.user_id, tags.tag_id HAVING SUM(purchase_price) >= ALL(SELECT SUM(purchase_price) FROM users")
    .append(" INNER JOIN orders ON users.user_id = orders.users_id_fk")
    .append(" INNER JOIN gift_certificates ON orders.gift_certificates_id_fk = gift_certificates.certificate_id")
    .append(" INNER JOIN gift_certificates_has_tags ON gift_certificates.certificate_id = gift_certificates_has_tags.gift_certificates_id_fk")
    .append(" INNER JOIN tags ON gift_certificates_has_tags.tags_id_fk = tags.tag_id GROUP BY users.user_id, tags.tag_id);").toString());

    private String query;

    TagQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

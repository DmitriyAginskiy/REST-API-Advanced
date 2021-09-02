package com.epam.esm.dao.constant;

/**
 * Class with the tags queries.
 *
 * @author Dzmitry Ahinski
 */
public final class TagQuery {

    public static final String FIND_ALL_TAGS = "SELECT * FROM tags LIMIT ?, ?;";

    public static final String FIND_BY_NAME = "SELECT * FROM tags WHERE tag_name = ?;";

    public static final String DISCONNECT_TAG_FROM_CERTIFICATES = "DELETE FROM gift_certificates_has_tags WHERE tags_id_fk = ?;";

    public static final String FIND_MOST_EXPENSIVE_TAG = new StringBuilder("SELECT tags.* FROM users")
    .append(" INNER JOIN orders ON users.user_id = orders.users_id_fk INNER JOIN gift_certificates ON orders.gift_certificates_id_fk = gift_certificates.certificate_id")
    .append(" INNER JOIN gift_certificates_has_tags ON gift_certificates.certificate_id = gift_certificates_has_tags.gift_certificates_id_fk")
    .append(" INNER JOIN tags ON gift_certificates_has_tags.tags_id_fk = tags.tag_id WHERE user_id = ?")
    .append(" GROUP BY tags.tag_id HAVING SUM(purchase_price) >= ALL(SELECT SUM(purchase_price) FROM users")
    .append(" INNER JOIN orders ON users.user_id = orders.users_id_fk")
    .append(" INNER JOIN gift_certificates ON orders.gift_certificates_id_fk = gift_certificates.certificate_id")
    .append(" INNER JOIN gift_certificates_has_tags ON gift_certificates.certificate_id = gift_certificates_has_tags.gift_certificates_id_fk")
    .append(" INNER JOIN tags ON gift_certificates_has_tags.tags_id_fk = tags.tag_id WHERE user_id = ? GROUP BY tags.tag_id);").toString();


    private TagQuery() {

    }
}

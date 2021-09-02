package com.epam.esm.dao.constant;

/**
 * Class with the order queries.
 *
 * @author Dzmitry Ahinski
 */
public class OrderQuery {

    public static final String BUY_CERTIFICATE_QUERY = "INSERT INTO orders (purchase_price, purchase_time, users_id_fk, gift_certificates_id_fk)"
            + " VALUES ((SELECT gift_certificates.price FROM gift_certificates WHERE certificate_id = ?), DATE_FORMAT("
            + " NOW(), '%Y-%m-%d %h:%i:%s') , ?, ?);";

    public static final String FIND_ORDERS_BY_USER = "SELECT * FROM orders WHERE users_id_fk = ? LIMIT ?, ?;";

    public static final String FIND_ORDER_BY_USER_AND_CERTIFICATE = "SELECT * FROM orders WHERE users_id_fk = ? AND"
            + " gift_certificates_id_fk = ?;";

    private OrderQuery() {

    }
}

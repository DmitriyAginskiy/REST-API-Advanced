package com.epam.esm.dao.constant;

/**
 * Class with the order queries.
 *
 * @author Dzmitry Ahinski
 */
public class OrderQuery {

    public static final String FIND_ORDERS_BY_USER = "SELECT * FROM orders WHERE users_id_fk = ? LIMIT ?, ?;";

    public static final String FIND_ORDER_BY_USER_AND_CERTIFICATE = "SELECT * FROM orders WHERE users_id_fk = ? AND"
            + " gift_certificates_id_fk = ?;";

    private OrderQuery() {

    }
}

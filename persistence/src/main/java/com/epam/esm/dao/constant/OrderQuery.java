package com.epam.esm.dao.constant;

/**
 * Enum with the order queries.
 *
 * @author Dzmitry Ahinski
 */
public enum OrderQuery {

    FIND_ORDERS_BY_USER("SELECT * FROM orders WHERE users_id_fk = ? LIMIT ?, ?;"),
    FIND_ORDER_BY_USER_AND_CERTIFICATE("SELECT * FROM orders WHERE users_id_fk = ? AND"
            + " gift_certificates_id_fk = ?;");

    private String query;

    OrderQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

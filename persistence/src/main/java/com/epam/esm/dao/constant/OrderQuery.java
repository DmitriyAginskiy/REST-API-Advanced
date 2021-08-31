package com.epam.esm.dao.constant;

public class OrderQuery {

    public static final String BUY_CERTIFICATE_QUERY = "INSERT INTO orders (purchasePrice, purchaseTime, users_id_fk, gift_certificates_id_fk)"
            + " VALUES ((SELECT gift_certificates.price FROM gift_certificates WHERE certificate_id = ?), NOW(), ?, ?);";

    private OrderQuery() {

    }
}

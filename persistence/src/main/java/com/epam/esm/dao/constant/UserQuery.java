package com.epam.esm.dao.constant;

/**
 * Enum with the user queries.
 *
 * @author Dzmitry Ahinski
 */
public enum UserQuery {

    FIND_ALL_QUERY("SELECT * FROM users LIMIT ?, ?;"),
    UPDATE_CASH_QUERY("UPDATE users SET cash = ? WHERE user_id = ?;");

    private String query;

    UserQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

package com.epam.esm.dao.constant;

/**
 * Class with the user queries.
 *
 * @author Dzmitry Ahinski
 */
public class UserQuery {

    public static final String FIND_ALL_QUERY = "SELECT * FROM users LIMIT ?, ?;";

    public static final String UPDATE_CASH_QUERY = "UPDATE users SET cash = ? WHERE user_id = ?;";

    private UserQuery() {

    }
}

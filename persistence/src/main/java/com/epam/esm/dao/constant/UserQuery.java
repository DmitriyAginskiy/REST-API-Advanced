package com.epam.esm.dao.constant;

/**
 * Class with the user queries.
 *
 * @author Dzmitry Ahinski
 */
public class UserQuery {

    public static final String FIND_ALL_QUERY = "SELECT * FROM users LIMIT ?, ?;";

    private UserQuery() {

    }
}

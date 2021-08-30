package com.epam.esm.dao.constant;

public class UserQuery {

    public static final String FIND_ALL_QUERY = "SELECT * FROM users LIMIT ?, ?;";

    public static final String FIND_BY_NAME_QUERY = "SELECT * FROM users WHERE user_name = ? LIMIT 1;";

    private UserQuery() {

    }
}

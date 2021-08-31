package com.epam.esm.dao.constant;

public class UserQuery {

    public static final String FIND_ALL_QUERY = "SELECT * FROM users LIMIT ?, ?;";

    private UserQuery() {

    }
}

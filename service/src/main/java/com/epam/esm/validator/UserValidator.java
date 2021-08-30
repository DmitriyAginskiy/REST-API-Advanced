package com.epam.esm.validator;

import java.util.regex.Pattern;

public class UserValidator {

    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z0-9\\w\\s]{2,25}");

    public static boolean isNameValid(String name) {
        return (name != null) && NAME_PATTERN.matcher(name).matches();
    }
}

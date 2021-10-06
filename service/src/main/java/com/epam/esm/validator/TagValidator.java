package com.epam.esm.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Validates tag.
 *
 * @author Dzmitry Ahinski
 */
@Component
public class TagValidator {

    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z0-9\\w\\s]{1,50}");

    public boolean isNameValid(String name) {
        return (name != null) && NAME_PATTERN.matcher(name).matches();
    }
}

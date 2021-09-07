package com.epam.esm.validator;

import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Validates gift certificate.
 *
 * @author Dzmitry Ahinski
 */
@Component
public class GiftCertificateValidator {

    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z0-9\\w\\s]{1,128}");

    public boolean isNameValid(String name) {
        return (name != null) && NAME_PATTERN.matcher(name).matches();
    }

    public boolean isDescriptionValid(String description) {
        return description != null && !description.isEmpty() && description.length() < 1000;
    }

    public boolean isPriceValid(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isDurationValid(int duration) {
        return duration > 0;
    }
}

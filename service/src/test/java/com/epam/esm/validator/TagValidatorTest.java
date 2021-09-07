package com.epam.esm.validator;

import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagValidatorTest {

    private static Tag tag = new Tag(1, "SomeName");
    private static TagValidator tagValidator = new TagValidator();

    @Test
    void isNameValid() {
        boolean actual = tagValidator.isNameValid(tag.getName());
        assertTrue(actual);
    }
}